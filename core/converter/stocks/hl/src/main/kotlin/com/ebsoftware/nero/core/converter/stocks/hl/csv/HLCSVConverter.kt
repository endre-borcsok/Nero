package com.ebsoftware.nero.core.converter.stocks.hl.csv

import com.ebsoftware.nero.core.converter.stocks.hl.HLConverter
import com.ebsoftware.nero.core.model.SecurityMovement
import com.opencsv.CSVReaderBuilder
import java.io.InputStream
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HLCSVConverter
    @Inject
    constructor() : HLConverter {
        override fun convert(inputStream: InputStream): List<SecurityMovement> {
            val reader = getReader(inputStream)
            val rows = reader.readAll()
            val headers =
                rows.findContains(DATE_COL)
                    ?: throw IllegalArgumentException("Missing header")
            val dateCol = headers.indexOfContains(DATE_COL)
            val quantityCol = headers.indexOfContains(QUANTITY_COL)
            val costCol = headers.indexOfContains(COST_COL)
            val positions = rows.drop(rows.indexOf(headers) + 2)
            return positions.map {
                SecurityMovement.EMPTY.copy(
                    date = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH).parse(it[dateCol])!!,
                    cost = it[costCol].toDouble(),
                    quantity = it[quantityCol].toDouble().toInt(),
                )
            }
        }

        private fun List<Array<String>>.findContains(text: String) = find { it.contains(text) }

        private fun Array<String>.indexOfContains(text: String) = indexOf(find { it.contains(text) })

        private fun getReader(inputStream: InputStream) =
            CSVReaderBuilder(InputStreamReader(inputStream))
                .build()

        companion object {
            const val DATE_COL = "DATE"
            const val QUANTITY_COL = "QUANTITY"
            const val COST_COL = "TOTAL COST"
        }
    }
