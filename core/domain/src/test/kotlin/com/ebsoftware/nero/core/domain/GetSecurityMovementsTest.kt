package com.ebsoftware.nero.core.domain

import com.ebsoftware.nero.core.converter.stocks.hl.HLConverter
import com.ebsoftware.nero.core.model.SecurityMovement
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.InputStream
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class GetSecurityMovementsTest {

    @Mock
    private lateinit var hlConverter: HLConverter

    @Test
    fun `when invoked then forwards data to converter`() = runTest {
        val file = mock<InputStream>()
        val files = List(3) { file }
        whenever(
            hlConverter.convert(file),
        ) doReturn listOf(SecurityMovement.EMPTY)
        assertEquals(
            expected = List(3) { SecurityMovement.EMPTY },
            actual = GetSecurityMovements(
                hlConverter = hlConverter,
            ).invoke(files),
        )
    }
}
