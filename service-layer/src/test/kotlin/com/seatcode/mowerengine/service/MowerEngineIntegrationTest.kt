package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MowerEngineIntegrationTest {
    private fun defaultHandlers(): Map<MowerAction, MowerActionHandler> = mapOf(
        RotateLeft to RotateLeftHandler(),
        RotateRight to RotateRightHandler(),
        MoveForward to MoveForwardHandler()
    )

    @Test
    fun `should execute actions and return final mower states`() {
        // Arrange
        val input: MowerEngineInput = MowerEngineInput(
            plateauMaxX = 5,
            plateauMaxY = 5,
            mowers = listOf(
                MowerInitData(
                    coordinates = Coordinates(1, 2),
                    direction = Direction.NORTH,
                    actions = listOf(MoveForward, RotateLeft, MoveForward)
                ),
                MowerInitData(
                    coordinates = Coordinates(3, 3),
                    direction = Direction.EAST,
                    actions = listOf(MoveForward, MoveForward, RotateRight, MoveForward)
                )
            )
        )
        val engine: MowerEngine = MowerEngine(defaultHandlers())
        // Act
        val result: MowerEngineResult = engine.execute(input)
        // Assert
        assertEquals(2, result.mowers.size)
        assertEquals(Coordinates(0, 4), result.mowers[0].coordinates)
        assertEquals(Direction.WEST, result.mowers[0].direction)
        assertEquals(Coordinates(5, 2), result.mowers[1].coordinates)
        assertEquals(Direction.SOUTH, result.mowers[1].direction)
    }

    @Test
    fun `should not move mower into occupied or invalid position`() {
        // Arrange
        val input: MowerEngineInput = MowerEngineInput(
            plateauMaxX = 5,
            plateauMaxY = 5,
            mowers = listOf(
                MowerInitData(
                    coordinates = Coordinates(0, 0),
                    direction = Direction.NORTH,
                    actions = listOf(MoveForward, RotateLeft, MoveForward)
                ),
                MowerInitData(
                    coordinates = Coordinates(0, 1),
                    direction = Direction.NORTH,
                    actions = listOf(MoveForward)
                )
            )
        )
        val engine: MowerEngine = MowerEngine(defaultHandlers())
        // Act
        val result: MowerEngineResult = engine.execute(input)
        // Assert
        assertEquals(2, result.mowers.size)
        // Mower1: couldn't move north due to occupied position
        // nor West due to plateau bounds
        assertEquals(Coordinates(0, 0), result.mowers[0].coordinates)
        assertEquals(Direction.WEST, result.mowers[0].direction)
        // Mower2: could move North
        assertEquals(Coordinates(0, 2), result.mowers[1].coordinates)
        assertEquals(Direction.NORTH, result.mowers[1].direction)
    }
}
