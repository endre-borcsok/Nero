package com.ebsoftware.nero.core.data.stocks.transform

import com.ebsoftware.nero.core.database.stocks.model.PositionEntity
import com.ebsoftware.nero.core.model.StockPosition
import java.util.Date

@JvmName("positionEntityList")
internal fun List<PositionEntity>.transform() =
    map(PositionEntity::transform)

@JvmName("stockPositionList")
internal fun List<StockPosition>.transform() =
    map(StockPosition::transform)

internal fun PositionEntity.transform() =
    StockPosition(
        ticker = ticker,
        quantity = count,
        cost = price,
        date = Date(dateUtcMs)
    )

internal fun StockPosition.transform() =
    PositionEntity(
        ticker = ticker,
        count = quantity,
        price = cost,
        dateUtcMs = date.time
    )