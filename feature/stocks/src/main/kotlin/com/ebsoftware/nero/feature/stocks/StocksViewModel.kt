package com.ebsoftware.nero.feature.stocks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.model.SecurityMovement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class StocksViewModel @Inject constructor(
    stockRepository: StockRepository
) : ViewModel() {
        val uiState: StateFlow<StocksUiState> =
            stockRepository
                .getSecurityMovements()
                .map<List<SecurityMovement>, StocksUiState> { StocksUiState.Success(it) }
                .catch { emit(StocksUiState.Error(it)) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.Lazily,
                    initialValue = StocksUiState.Loading,
                )
    }

internal interface StocksUiState {
    data object Loading : StocksUiState

    data class Error(val throwable: Throwable) : StocksUiState

    data class Success(val securityMovements: List<SecurityMovement>) : StocksUiState
}
