package com.ebsoftware.nero.feature.securitymovements.transform

import com.ebsoftware.nero.core.model.SecurityMovement
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementGainViewData
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals

class ViewTransformerTest {

    @Test
    fun `when transforms SecurityMovements then mapping is correct`() {
        val date = Date()
        assertEquals(
            expected = SecurityMovementGainViewData(
                id = "id",
                ticker = "ticker",
                cost = 300.0,
                quantity = 3,
                gainPercent = 50.0,
                gainAmount = 150.0,
            ),
            actual = SecurityMovement(
                id = "id",
                ticker = "ticker",
                cost = 300.0,
                quantity = 3,
                date = date,
            ).transform(
                latestPrice = 150.0,
            ),
        )
    }
}
