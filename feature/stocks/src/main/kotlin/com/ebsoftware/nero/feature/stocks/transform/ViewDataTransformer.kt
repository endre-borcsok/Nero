package com.ebsoftware.nero.feature.stocks.transform

import com.ebsoftware.nero.core.model.SecurityMovement
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData

internal fun SecurityMovement.transform() = SecurityMovementViewData(
    id = id,
    ticker = ticker,
    quantity = quantity,
    date = date,
    cost = cost,
)
