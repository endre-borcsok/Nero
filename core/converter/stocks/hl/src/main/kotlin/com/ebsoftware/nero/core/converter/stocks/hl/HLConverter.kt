package com.ebsoftware.nero.core.converter.stocks.hl

import com.ebsoftware.nero.core.model.SecurityMovement
import java.io.InputStream

interface HLConverter {

    suspend fun convert(
        inputStream: InputStream,
    ): List<SecurityMovement>
}
