package com.ebsoftware.nero.feature.stocks.transfrom

import com.ebsoftware.nero.core.model.SecurityMovement
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData
import com.ebsoftware.nero.feature.stocks.transform.transform
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals

class ViewTransformerTest {

    @Test
    fun `when transforms SecurityMovements then mapping is correct`() {
        val date = Date()
        assertEquals(
            expected = SecurityMovementViewData(
                id = "id",
                ticker = "ticker",
                cost = 123.45676,
                quantity = 999,
                date = date,
            ),
            actual = SecurityMovement(
                id = "id",
                ticker = "ticker",
                cost = 123.45676,
                quantity = 999,
                date = date,
            ).transform(),
        )
    }

    @Test
    fun `when transforms SecurityMovementsViewData then mapping is correct`() {
        val date = Date()
        assertEquals(
            expected = SecurityMovement(
                id = "id",
                ticker = "ticker",
                cost = 123.45676,
                quantity = 999,
                date = date,
            ),
            actual = SecurityMovementViewData(
                id = "id",
                ticker = "ticker",
                cost = 123.45676,
                quantity = 999,
                date = date,
            ).transform(),
        )
    }
}
