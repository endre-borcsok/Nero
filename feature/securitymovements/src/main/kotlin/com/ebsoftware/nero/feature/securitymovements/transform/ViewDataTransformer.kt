package com.ebsoftware.nero.feature.securitymovements.transform

import com.ebsoftware.nero.core.model.SecurityMovement
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementGainViewData

internal fun SecurityMovement.transform(
    latestPrice: Double,
) = SecurityMovementGainViewData(
    id = id,
    ticker = ticker,
    quantity = quantity,
    cost = cost,
    gainPercent = (((latestPrice * quantity) - cost) / cost) * 100, // (profit - cost) * 100
    gainAmount = (latestPrice * quantity) - cost, // profit
)
