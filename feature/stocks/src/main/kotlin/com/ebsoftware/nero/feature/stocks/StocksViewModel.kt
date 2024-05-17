package com.ebsoftware.nero.feature.stocks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.domain.GetAggregatedSecurityMovements
import com.ebsoftware.nero.core.domain.GetSecurityMovements
import com.ebsoftware.nero.core.model.SecurityMovement
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData
import com.ebsoftware.nero.feature.stocks.transform.transform
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
internal class StocksViewModel @Inject constructor(
    private val stockRepository: StockRepository,
    private val getSecurityMovements: GetSecurityMovements,
    private val getAggregatedSecurityMovements: GetAggregatedSecurityMovements,
) : ViewModel() {

    val uiState: StateFlow<StocksUiState> =
        stockRepository
            .getSecurityMovements()
            .map<List<SecurityMovement>, StocksUiState> { securityMovements ->
                StocksUiState.Success(
                    aggregatedSecurityMovements = securityMovements
                        .run(getAggregatedSecurityMovements::invoke)
                        .map(SecurityMovement::transform),
                )
            }
            .catch { emit(StocksUiState.Error(it)) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = StocksUiState.Loading,
            )

    fun addSecurityMovements(
        files: List<InputStream>,
    ) {
        viewModelScope.launch {
            stockRepository.addSecurityMovements(
                securityMovements = getSecurityMovements(files),
            )
        }
    }

    fun updateSecurityMovementsByAggregatedItem(
        aggregatedSecurityMovement: SecurityMovementViewData,
    ) {
        viewModelScope.launch {
            val persistedSecurityMovement =
                stockRepository.getSecurityMovementById(aggregatedSecurityMovement.id)
            val siblingSecurityMovements =
                stockRepository.getSecurityMovementsByTicker(persistedSecurityMovement.ticker).first()
            val updatedSecurityMovements =
                siblingSecurityMovements.map {
                    it.copy(ticker = aggregatedSecurityMovement.ticker)
                }
            stockRepository.addSecurityMovements(updatedSecurityMovements)
        }
    }
}

internal sealed interface StocksUiState {
    data object Loading : StocksUiState
    data class Error(val throwable: Throwable) : StocksUiState
    data class Success(val aggregatedSecurityMovements: List<SecurityMovementViewData>) : StocksUiState
}
