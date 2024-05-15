package com.ebsoftware.nero.feature.stocks

import android.net.Uri
import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.domain.GetSecurityMovements
import com.ebsoftware.nero.core.model.SecurityMovement
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class StocksViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var stockRepository: StockRepository

    @Mock
    private lateinit var getSecurityMovements: GetSecurityMovements

    private fun initViewModel() = StocksViewModel(
        stockRepository = stockRepository,
        getSecurityMovements = getSecurityMovements,
    )

    @Test
    fun `when initialised the initial ui state is Loading`() {
        assertTrue(initViewModel().uiState.value is StocksUiState.Loading)
    }

    @Test
    fun `when security movements are collected successfully then ui state is Success`() = runTest {
        val movements = listOf(SecurityMovement.EMPTY)
        whenever(stockRepository.getSecurityMovements()) doReturn flowOf(movements)
        assertEquals(
            expected = StocksUiState.Success(movements),
            actual = initViewModel().uiState.first(),
        )
    }

    @Test
    fun `when security movements are collected unsuccessfully then ui state is Error`() = runTest {
        val exception = Exception("Test exception")
        whenever(stockRepository.getSecurityMovements()) doReturn flow { throw exception }
        assertEquals(
            expected = StocksUiState.Error(exception),
            actual = initViewModel().uiState.first(),
        )
    }

    @Test
    fun `when security movement files are added then they are forwarded to the repository as domain models`() = runTest {
        val viewModel = initViewModel()
        val uris = List(3) { mock<Uri>() }
        val securityMovements = List(3) { mock<SecurityMovement>() }
        whenever(
            getSecurityMovements.invoke(uris),
        ).doReturn(securityMovements)
        viewModel.addSecurityMovements(uris)
        verify(stockRepository).addSecurityMovements(securityMovements)
    }
}
