package com.ebsoftware.nero.core.data.stocks.transform

import com.ebsoftware.nero.core.database.stocks.model.SecurityMovementEntity
import com.ebsoftware.nero.core.model.SecurityMovement
import java.util.Date

@JvmName("positionEntityList")
internal fun List<SecurityMovementEntity>.transform() = map(SecurityMovementEntity::transform)

@JvmName("stockPositionList")
internal fun List<SecurityMovement>.transform() = map(SecurityMovement::transform)

internal fun SecurityMovementEntity.transform() =
    SecurityMovement(
        ticker = ticker,
        quantity = quantity,
        cost = price,
        date = Date(dateUtcMs),
    )

internal fun SecurityMovement.transform() =
    SecurityMovementEntity(
        ticker = ticker,
        quantity = quantity,
        price = cost,
        dateUtcMs = date.time,
    )
