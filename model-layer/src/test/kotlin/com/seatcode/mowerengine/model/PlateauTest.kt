package com.seatcode.mowerengine.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlateauTest {
    @Test
    fun `areValidCoordinates should return true for coordinates inside plateau`() {
        // Arrange
        val plateau: Plateau = Plateau(5, 5)
        val coordinates: Coordinates = Coordinates(3, 4)
        // Act
        val result: Boolean = plateau.areValidCoordinates(coordinates)
        // Assert
        assertTrue(result)
    }

    @Test
    fun `areValidCoordinates should return true for coordinates at origin`() {
        // Arrange
        val plateau: Plateau = Plateau(5, 5)
        val coordinates: Coordinates = Coordinates(0, 0)
        // Act
        val result: Boolean = plateau.areValidCoordinates(coordinates)
        // Assert
        assertTrue(result)
    }

    @Test
    fun `areValidCoordinates should return true for coordinates at upper bounds`() {
        // Arrange
        val plateau: Plateau = Plateau(5, 5)
        val coordinates: Coordinates = Coordinates(5, 5)
        // Act
        val result: Boolean = plateau.areValidCoordinates(coordinates)
        // Assert
        assertTrue(result)
    }

    @Test
    fun `areValidCoordinates should return false for negative x`() {
        // Arrange
        val plateau: Plateau = Plateau(5, 5)
        val coordinates: Coordinates = Coordinates(-1, 2)
        // Act
        val result: Boolean = plateau.areValidCoordinates(coordinates)
        // Assert
        assertFalse(result)
    }

    @Test
    fun `areValidCoordinates should return false for negative y`() {
        // Arrange
        val plateau: Plateau = Plateau(5, 5)
        val coordinates: Coordinates = Coordinates(2, -1)
        // Act
        val result: Boolean = plateau.areValidCoordinates(coordinates)
        // Assert
        assertFalse(result)
    }

    @Test
    fun `areValidCoordinates should return false for x greater than maxX`() {
        // Arrange
        val plateau: Plateau = Plateau(5, 5)
        val coordinates: Coordinates = Coordinates(6, 3)
        // Act
        val result: Boolean = plateau.areValidCoordinates(coordinates)
        // Assert
        assertFalse(result)
    }

    @Test
    fun `areValidCoordinates should return false for y greater than maxY`() {
        // Arrange
        val plateau: Plateau = Plateau(5, 5)
        val coordinates: Coordinates = Coordinates(3, 6)
        // Act
        val result: Boolean = plateau.areValidCoordinates(coordinates)
        // Assert
        assertFalse(result)
    }

    @Test
    fun `areValidCoordinates should return false if position is occupied by another mower`() {
        // Arrange
        val plateau: Plateau = Plateau(5, 5)
        val mower1: Mower = Mower(Coordinates(2, 2), Direction.NORTH)
        val mower2: Mower = Mower(Coordinates(2, 2), Direction.EAST)
        plateau.addMower(mower1)
        // Act
        val result: Boolean = plateau.areValidCoordinates(mower2.getCurrentCoordinates())
        // Assert
        assertFalse(result)
    }

    @Test
    fun `addMower should add mower if position is free`() {
        // Arrange
        val plateau: Plateau = Plateau(5, 5)
        val mower: Mower = Mower(Coordinates(1, 1), Direction.NORTH)
        // Act
        val added: Boolean = plateau.addMower(mower)
        // Assert
        assertTrue(added)
    }

    @Test
    fun `addMower should not add mower if position is occupied`() {
        // Arrange
        val plateau: Plateau = Plateau(5, 5)
        val mower1: Mower = Mower(Coordinates(3, 3), Direction.NORTH)
        val mower2: Mower = Mower(Coordinates(3, 3), Direction.EAST)
        plateau.addMower(mower1)
        // Act
        val added: Boolean = plateau.addMower(mower2)
        // Assert
        assertFalse(added)
        assertEquals(1, plateau.getMowers().size)
    }
}
