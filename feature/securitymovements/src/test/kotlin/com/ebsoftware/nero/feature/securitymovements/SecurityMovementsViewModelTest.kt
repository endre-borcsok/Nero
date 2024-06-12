package com.ebsoftware.nero.feature.securitymovements

import androidx.lifecycle.SavedStateHandle
import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.model.SecurityMovement
import com.ebsoftware.nero.feature.securitymovements.navigation.ARG_TICKER_SYMBOL
import com.ebsoftware.nero.feature.securitymovements.transform.transform
import com.ebsoftware.nero.testing.jvm.MainDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class SecurityMovementsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var mockStockRepository: StockRepository

    private val savedStateHandle = SavedStateHandle()

    private fun initViewModel(
        stockRepository: StockRepository = mockStockRepository,
    ) = SecurityMovementsViewModel(
        savedStateHandle = savedStateHandle,
        stockRepository = stockRepository,
    )

    @Test
    fun `when initialised then default state is Loading`() {
        assertTrue(initViewModel().uiState.value is SecurityMovementsUiState.Loading)
    }

    @Test
    fun `when initialised successfully then state is Success`() = runTest {
        savedStateHandle[ARG_TICKER_SYMBOL] = "AAPL"
        val viewModel = initViewModel()
        val latestPrice = viewModel.latestPrice.first()
        val securityMovements = listOf(SecurityMovement.EMPTY)
        whenever(
            mockStockRepository.getSecurityMovementsByTicker("AAPL"),
        ) doReturn flowOf(securityMovements)
        assertEquals(
            expected = SecurityMovementsUiState.Success(
                securityMovements = securityMovements.map { it.transform(latestPrice) },
            ),
            actual = viewModel.uiState.first(),
        )
    }

    @Test
    fun `when initialised unsuccessfully then state is Error`() = runTest {
        savedStateHandle[ARG_TICKER_SYMBOL] = "AAPL"
        whenever(
            mockStockRepository.getSecurityMovementsByTicker("AAPL"),
        ) doReturn flow { throw Exception("Test exception") }
        with(initViewModel().uiState.first()) {
            assertTrue(this is SecurityMovementsUiState.Error)
            assertEquals(
                expected = "Test exception",
                actual = throwable.message,
            )
        }
    }

    @Test
    fun `when initialised then uses last security movement to declare latest price`() = runTest {
        savedStateHandle[ARG_TICKER_SYMBOL] = "AAPL"
        val viewModel = initViewModel()
        val securityMovements = listOf(
            SecurityMovement.EMPTY.copy(cost = 66.0, quantity = 2),
            SecurityMovement.EMPTY.copy(cost = 100.0, quantity = 2),
        )
        whenever(
            mockStockRepository.getSecurityMovementsByTicker("AAPL"),
        ) doReturn flowOf(securityMovements)
        assertEquals(
            expected = 50.0,
            actual = viewModel.latestPrice.first(),
        )
    }
}
