package com.ebsoftware.nero.core.domain

import com.ebsoftware.nero.core.converter.stocks.hl.HLConverter
import com.ebsoftware.nero.core.model.SecurityMovement
import java.io.InputStream
import javax.inject.Inject

class GetSecurityMovements @Inject constructor(
    private val hlConverter: HLConverter,
) {
    suspend operator fun invoke(
        files: List<InputStream>,
    ): List<SecurityMovement> {
        return files.flatMap { hlConverter.convert(it) }
    }
}
