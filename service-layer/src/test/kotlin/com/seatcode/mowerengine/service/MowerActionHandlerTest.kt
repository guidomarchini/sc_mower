package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MowerActionHandlerTest {
    @Test
    fun `RotateLeftHandler should rotate mower to the left`() {
        // Arrange
        val mower: Mower = Mower(Coordinates(0, 0), Direction.NORTH)
        val handler: MowerActionHandler = RotateLeftHandler()
        val plateau: Plateau = Plateau(5, 5)
        // Act
        handler.handle(mower, plateau)
        // Assert
        assertEquals(Direction.WEST, mower.getCurrentDirection())
    }

    @Test
    fun `RotateRightHandler should rotate mower to the right`() {
        // Arrange
        val mower: Mower = Mower(Coordinates(0, 0), Direction.NORTH)
        val handler: MowerActionHandler = RotateRightHandler()
        val plateau: Plateau = Plateau(5, 5)
        // Act
        handler.handle(mower, plateau)
        // Assert
        assertEquals(Direction.EAST, mower.getCurrentDirection())
    }

    @Test
    fun `MoveForwardHandler should update mower's coordinates if valid`() {
        // Arrange
        val mower: Mower = Mower(Coordinates(1, 1), Direction.NORTH)
        val handler: MowerActionHandler = MoveForwardHandler()
        val plateau: Plateau = Plateau(5, 5)
        // Act
        handler.handle(mower, plateau)
        // Assert
        assertEquals(Coordinates(1, 2), mower.getCurrentCoordinates())
    }

    @Test
    fun `MoveForwardHandler should not update mower's coordinates if invalid`() {
        // Arrange
        val mower: Mower = Mower(Coordinates(5, 5), Direction.NORTH)
        val handler: MowerActionHandler = MoveForwardHandler()
        val plateau: Plateau = Plateau(5, 5)
        // Act
        handler.handle(mower, plateau)
        // Assert
        assertEquals(Coordinates(5, 5), mower.getCurrentCoordinates())
    }
}

