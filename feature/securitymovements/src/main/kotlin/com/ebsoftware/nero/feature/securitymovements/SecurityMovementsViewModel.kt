package com.ebsoftware.nero.feature.securitymovements

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.model.SecurityMovement
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData
import com.ebsoftware.nero.feature.securitymovements.navigation.ARG_TICKER_SYMBOL
import com.ebsoftware.nero.feature.securitymovements.transform.transform
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class SecurityMovementsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    stockRepository: StockRepository,
) : ViewModel() {

    private val tickerSymbol = savedStateHandle
        .getStateFlow(
            key = ARG_TICKER_SYMBOL,
            initialValue = String(),
        )

    val uiState: StateFlow<SecurityMovementsUiState> = tickerSymbol
        .flatMapLatest { ticker ->
            stockRepository.getSecurityMovementsByTicker(
                ticker = ticker,
            )
        }.map<List<SecurityMovement>, SecurityMovementsUiState> { securityMovements ->
            SecurityMovementsUiState.Success(
                securityMovements = securityMovements.map(SecurityMovement::transform),
            )
        }.catch {
            emit(SecurityMovementsUiState.Error(it))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = SecurityMovementsUiState.Loading,
        )
}

internal sealed interface SecurityMovementsUiState {
    data object Loading : SecurityMovementsUiState
    data class Error(val throwable: Throwable) : SecurityMovementsUiState
    data class Success(val securityMovements: List<SecurityMovementViewData>) : SecurityMovementsUiState
}
