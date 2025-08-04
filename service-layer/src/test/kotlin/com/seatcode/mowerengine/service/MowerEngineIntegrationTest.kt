package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.Coordinates
import com.seatcode.mowerengine.model.Direction
import com.seatcode.mowerengine.model.MowerAction
import com.seatcode.mowerengine.service.handler.MoveForwardHandler
import com.seatcode.mowerengine.service.handler.MowerActionHandler
import com.seatcode.mowerengine.service.handler.RotateLeftHandler
import com.seatcode.mowerengine.service.handler.RotateRightHandler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MowerEngineIntegrationTest {
    private fun defaultHandlers(): List<MowerActionHandler> = listOf(
        RotateLeftHandler(),
        RotateRightHandler(),
        MoveForwardHandler()
    )
    val engine: MowerEngineContract = SequentialMowerEngine(defaultHandlers())

    @Test
    fun `should execute actions and return final mower states`() {
        // Arrange
        val input: MowerEngineInput = MowerEngineInput(
            plateauMaxX = 5,
            plateauMaxY = 5,
            mowerInitDatas = listOf(
                MowerInitData(
                    coordinates = Coordinates(1, 2),
                    direction = Direction.NORTH,
                    actions = listOf(MowerAction.MOVE_FORWARD, MowerAction.ROTATE_LEFT, MowerAction.MOVE_FORWARD)
                ),
                MowerInitData(
                    coordinates = Coordinates(3, 3),
                    direction = Direction.EAST,
                    actions = listOf(MowerAction.MOVE_FORWARD, MowerAction.MOVE_FORWARD, MowerAction.ROTATE_RIGHT, MowerAction.MOVE_FORWARD)
                )
            )
        )
        // Act
        val result: MowerEngineResult = engine.execute(input)
        // Assert
        assertEquals(2, result.mowerResults.size)
        assertEquals(Coordinates(0, 3), result.mowerResults[0].coordinates)
        assertEquals(Direction.WEST, result.mowerResults[0].direction)
        assertEquals(Coordinates(5, 2), result.mowerResults[1].coordinates)
        assertEquals(Direction.SOUTH, result.mowerResults[1].direction)
    }

    @Test
    fun `should not move mower into occupied or invalid position`() {
        // Arrange
        val input: MowerEngineInput = MowerEngineInput(
            plateauMaxX = 5,
            plateauMaxY = 5,
            mowerInitDatas = listOf(
                MowerInitData(
                    coordinates = Coordinates(0, 0),
                    direction = Direction.WEST,
                    actions = listOf(MowerAction.ROTATE_RIGHT, MowerAction.MOVE_FORWARD, MowerAction.MOVE_FORWARD)
                ),
                MowerInitData(
                    coordinates = Coordinates(0, 1),
                    direction = Direction.NORTH,
                    actions = listOf(MowerAction.MOVE_FORWARD, MowerAction.MOVE_FORWARD)
                )
            )
        )

        // Act
        val result: MowerEngineResult = engine.execute(input)
        // Assert
        assertEquals(2, result.mowerResults.size)
        assertEquals(Coordinates(0, 0), result.mowerResults[0].coordinates)
        assertEquals(Direction.NORTH, result.mowerResults[0].direction)
        assertEquals(Coordinates(0, 3), result.mowerResults[1].coordinates)
        assertEquals(Direction.NORTH, result.mowerResults[1].direction)
    }
}
