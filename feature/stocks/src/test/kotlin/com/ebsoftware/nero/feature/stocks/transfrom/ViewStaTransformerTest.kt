package com.ebsoftware.nero.feature.stocks.transfrom

import com.ebsoftware.nero.core.model.SecurityMovement
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData
import com.ebsoftware.nero.feature.stocks.transform.transform
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals

class ViewStaTransformerTest {

    @Test
    fun `when transforms SecurityMovements then mapping is correct`() {
        val date = Date()
        assertEquals(
            expected = SecurityMovementViewData(
                ticker = "ticker",
                cost = 123.45676,
                quantity = 999,
                date = date,
            ),
            actual = SecurityMovement(
                ticker = "ticker",
                cost = 123.45676,
                quantity = 999,
                date = date,
            ).transform(),
        )
    }
}
