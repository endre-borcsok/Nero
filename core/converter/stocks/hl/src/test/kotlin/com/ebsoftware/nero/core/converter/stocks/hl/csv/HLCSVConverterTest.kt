package com.ebsoftware.nero.core.converter.stocks.hl.csv

import com.ebsoftware.nero.core.model.SecurityMovement
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.test.assertEquals

class HLCSVConverterTest {
    @Test
    fun `when CSV file converter is used then data is mapped correctly`() = runTest {
        val testStream =
            "Security movements for:, Nice Company, Common Stock USD 0.01 (CDI) , , ,\n" +
                "Client Name: Mr John Smith , , , , ,\n" +
                "Client Number: 00000 , , , , ,\n" +
                "\n" +
                "HL Stocks & Shares ISA , , , ,\n" +
                "\n" +
                "\n" +
                "DATE, TYPE, REFERENCE, UNIT COST (p), QUANTITY, TOTAL COST (�)\n" +
                "\n" +
                "04/04/2024,Corp.A,E 01251223,0.00,0.00,0.00,\n" +
                "04/04/2024,Corp.A,E 01251223,0.00,0.00,-286.76,\n" +
                "14/01/2020,Bought,B533982422,13968.87,3.00,435.21,"
        val converter = HLCSVConverter()
        val movements = converter.convert(testStream.byteInputStream())
        assertEquals(
            expected =
            listOf(
                SecurityMovement.EMPTY.copy(
                    date =
                    SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                        .parse("04/04/2024")!!,
                    cost = 0.00,
                    quantity = 0,
                ),
                SecurityMovement.EMPTY.copy(
                    date =
                    SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                        .parse("04/04/2024")!!,
                    cost = -286.76,
                    quantity = 0,
                ),
                SecurityMovement.EMPTY.copy(
                    date =
                    SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                        .parse("14/01/2020")!!,
                    cost = 435.21,
                    quantity = 3,
                ),
            ),
            actual = movements,
        )
    }
}
