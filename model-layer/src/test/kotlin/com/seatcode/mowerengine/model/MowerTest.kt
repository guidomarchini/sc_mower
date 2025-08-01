package com.seatcode.mowerengine.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MowerTest {
    @Test
    fun `rotateLeft should update direction correctly`() {
        // Arrange
        val mower: Mower = Mower(Coordinates(0, 0), Direction.NORTH)
        // Act
        mower.rotateLeft()
        // Assert
        assertEquals(Direction.WEST, mower.getCurrentDirection())
    }

    @Test
    fun `rotateRight should update direction correctly`() {
        // Arrange
        val mower: Mower = Mower(Coordinates(0, 0), Direction.NORTH)
        // Act
        mower.rotateRight()
        // Assert
        assertEquals(Direction.EAST, mower.getCurrentDirection())
    }

    @Test
    fun `calculateNextCoordinates should return correct coordinates`() {
        // Arrange
        val mower: Mower = Mower(Coordinates(1, 1), Direction.NORTH)
        // Act
        val next: Coordinates = mower.calculateNextCoordinates()
        // Assert
        assertEquals(Coordinates(1, 2), next)
    }

    @Test
    fun `updateCoordinates should update mower's coordinates`() {
        // Arrange
        val mower: Mower = Mower(Coordinates(1, 1), Direction.NORTH)
        val newCoordinates: Coordinates = Coordinates(2, 3)
        // Act
        mower.updateCoordinates(newCoordinates)
        // Assert
        assertEquals(newCoordinates, mower.getCurrentCoordinates())
    }
}

