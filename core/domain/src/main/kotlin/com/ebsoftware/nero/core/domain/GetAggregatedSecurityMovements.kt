package com.ebsoftware.nero.core.domain

import com.ebsoftware.nero.core.model.SecurityMovement
import javax.inject.Inject

class GetAggregatedSecurityMovements @Inject constructor() {
    operator fun invoke(
        securityMovements: List<SecurityMovement>,
    ) = securityMovements.groupBy(SecurityMovement::ticker)
        .map { entry ->
            entry.value.fold(entry.value.first()) { prev, curr ->
                if (prev != curr) {
                    prev.copy(
                        cost = prev.cost + curr.cost,
                        quantity = prev.quantity + curr.quantity,
                        date = if (curr.date > prev.date) curr.date else prev.date,
                    )
                } else {
                    curr
                }
            }
        }
}
