package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.Coordinates
import com.seatcode.mowerengine.model.Direction
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class MowerEngineInputValidatorTest {
    @Test
    fun `should throw if plateau size is negative`() {
        // Arrange
        val input = MowerEngineInput(-1, 5, emptyList())
        // Act & Assert
        assertThrows(IllegalArgumentException::class.java) {
            MowerEngineInputValidator.validate(input)
        }
    }

    @Test
    fun `should throw if mower is out of plateau bounds`() {
        // Arrange
        val input = MowerEngineInput(
            5, 5,
            listOf(MowerInitData(Coordinates(6, 2), Direction.NORTH, emptyList()))
        )
        // Act & Assert
        assertThrows(IllegalArgumentException::class.java) {
            MowerEngineInputValidator.validate(input)
        }
    }

    @Test
    fun `should throw if two mowers have the same initial position`() {
        // Arrange
        val input = MowerEngineInput(
            5, 5,
            listOf(
                MowerInitData(Coordinates(1, 1), Direction.NORTH, emptyList()),
                MowerInitData(Coordinates(1, 1), Direction.EAST, emptyList())
            )
        )
        // Act & Assert
        assertThrows(IllegalArgumentException::class.java) {
            MowerEngineInputValidator.validate(input)
        }
    }

    @Test
    fun `should not throw for valid input`() {
        // Arrange
        val input = MowerEngineInput(
            5, 5,
            listOf(
                MowerInitData(Coordinates(1, 1), Direction.NORTH, emptyList()),
                MowerInitData(Coordinates(2, 2), Direction.EAST, emptyList())
            )
        )
        // Act & Assert
        MowerEngineInputValidator.validate(input) // should not throw
    }
}
