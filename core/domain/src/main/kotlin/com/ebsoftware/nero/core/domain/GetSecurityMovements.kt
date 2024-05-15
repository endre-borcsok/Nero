package com.ebsoftware.nero.core.domain

import android.net.Uri
import com.ebsoftware.nero.core.converter.stocks.hl.HLConverter
import com.ebsoftware.nero.core.model.SecurityMovement
import javax.inject.Inject

class GetSecurityMovements @Inject constructor(
    private val hlConverter: HLConverter,
) {
    suspend operator fun invoke(
        uris: List<Uri>,
    ): List<SecurityMovement> {
        return emptyList()
    }
}
