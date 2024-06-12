package com.ebsoftware.nero.feature.securitymovements

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.model.SecurityMovement
import com.ebsoftware.nero.core.model.price
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementGainViewData
import com.ebsoftware.nero.feature.securitymovements.navigation.ARG_TICKER_SYMBOL
import com.ebsoftware.nero.feature.securitymovements.transform.transform
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

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

    private val securityMovements = tickerSymbol
        .flatMapLatest { ticker ->
            stockRepository.getSecurityMovementsByTicker(
                ticker = ticker,
            )
        }

    private val _latestPrice = MutableStateFlow<Double?>(null)
    val latestPrice: StateFlow<Double> =
        combine(
            securityMovements.asResult(),
            _latestPrice,
        ) { securityMovements, latestPrice ->
            latestPrice ?: securityMovements.fold(
                onSuccess = { it.last().let(SecurityMovement::price) },
                onFailure = { 0.0 },
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = 0.0,
        )

    val uiState: StateFlow<SecurityMovementsUiState> =
        combine(
            securityMovements.asResult(),
            latestPrice,
        ) { securityMovements, latestPrice ->
            securityMovements.fold(
                onSuccess = { movements ->
                    SecurityMovementsUiState.Success(
                        securityMovements = movements.map { it.transform(latestPrice) },
                    )
                },
                onFailure = { SecurityMovementsUiState.Error(it) },
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = SecurityMovementsUiState.Loading,
        )
}

internal sealed interface SecurityMovementsUiState {
    data object Loading : SecurityMovementsUiState
    data class Error(val throwable: Throwable) : SecurityMovementsUiState
    data class Success(val securityMovements: List<SecurityMovementGainViewData>) : SecurityMovementsUiState
}

private fun Flow<List<SecurityMovement>>.asResult() = map(::success).catch { emit(failure(it)) }
