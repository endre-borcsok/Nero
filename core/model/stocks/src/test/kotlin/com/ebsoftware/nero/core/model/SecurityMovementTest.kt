package com.ebsoftware.nero.core.model

import org.junit.Test
import kotlin.test.assertEquals

class SecurityMovementTest {

    @Test
    fun `when invokes price then divides cost by quantity`() {
        assertEquals(
            expected = 20.0,
            actual = SecurityMovement.EMPTY.copy(
                quantity = 10,
                cost = 200.0,
            ).price(),
        )
    }
}
