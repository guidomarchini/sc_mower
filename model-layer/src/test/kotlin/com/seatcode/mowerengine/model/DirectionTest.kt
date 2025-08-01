package com.seatcode.mowerengine.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DirectionTest {
    companion object {
        @JvmStatic
        fun rotateLeftCases() = listOf(
            arrayOf(Direction.NORTH, Direction.WEST),
            arrayOf(Direction.EAST, Direction.NORTH),
            arrayOf(Direction.SOUTH, Direction.EAST),
            arrayOf(Direction.WEST, Direction.SOUTH)
        )

        @JvmStatic
        fun rotateRightCases() = listOf(
            arrayOf(Direction.NORTH, Direction.EAST),
            arrayOf(Direction.EAST, Direction.SOUTH),
            arrayOf(Direction.SOUTH, Direction.WEST),
            arrayOf(Direction.WEST, Direction.NORTH)
        )

        @JvmStatic
        fun moveForwardCases() = listOf(
            arrayOf(Direction.NORTH, Coordinates(1, 1), Coordinates(1, 2)),
            arrayOf(Direction.EAST, Coordinates(1, 1), Coordinates(2, 1)),
            arrayOf(Direction.SOUTH, Coordinates(1, 1), Coordinates(1, 0)),
            arrayOf(Direction.WEST, Coordinates(1, 1), Coordinates(0, 1))
        )
    }

    @ParameterizedTest
    @MethodSource("rotateLeftCases")
    fun `rotateLeft should return correct direction`(
        origin: Direction,
        expected: Direction
    ) {
        // Act
        val result = origin.rotateLeft()
        // Assert
        assertEquals(expected, result, "rotateLeft from $origin should return $expected")
    }

    @ParameterizedTest
    @MethodSource("rotateRightCases")
    fun `rotateRight should return correct direction`(
        origin: Direction,
        expected: Direction
    ) {
        // Act
        val result = origin.rotateRight()
        // Assert
        assertEquals(expected, result, "rotateRight from $origin should return $expected")
    }

    @ParameterizedTest
    @MethodSource("moveForwardCases")
    fun `moveForward should return correct coordinates`(
        direction: Direction,
        start: Coordinates,
        expected: Coordinates
    ) {
        // Act
        val result = direction.moveForward(start)
        // Assert
        assertEquals(expected, result, "moveForward from $direction at $start should return $expected")
    }
}
