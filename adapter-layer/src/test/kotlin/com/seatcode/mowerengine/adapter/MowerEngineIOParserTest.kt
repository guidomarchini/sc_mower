package com.seatcode.mowerengine.adapter

import com.seatcode.mowerengine.model.Coordinates
import com.seatcode.mowerengine.model.Direction
import com.seatcode.mowerengine.model.MowerAction
import com.seatcode.mowerengine.service.MowerEngineInput
import com.seatcode.mowerengine.service.MowerEngineResult
import com.seatcode.mowerengine.service.MowerResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MowerEngineIOParserTest {
    @Test
    fun `parseInput should parse valid input with one mower`() {
        // Arrange
        val input = """
            5 5
            1 2 N
            LMLMLMLMM
        """.trimIndent()
        // Act
        val result: MowerEngineInput = MowerEngineIOParser.parseInput(input)
        // Assert
        assertEquals(5, result.plateauMaxX)
        assertEquals(5, result.plateauMaxY)
        assertEquals(1, result.mowerInitDatas.size)
        val mower = result.mowerInitDatas[0]
        assertEquals(1, mower.coordinates.x)
        assertEquals(2, mower.coordinates.y)
        assertEquals(Direction.NORTH, mower.direction)
        assertEquals(
            listOf<MowerAction>(
                MowerAction.ROTATE_LEFT, MowerAction.MOVE_FORWARD, MowerAction.ROTATE_LEFT, MowerAction.MOVE_FORWARD,
                MowerAction.ROTATE_LEFT, MowerAction.MOVE_FORWARD, MowerAction.ROTATE_LEFT, MowerAction.MOVE_FORWARD, MowerAction.MOVE_FORWARD
            ),
            mower.actions
        )
    }

    @Test
    fun `parseInput should parse valid input with two mowers`() {
        // Arrange
        val input = """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent()
        // Act
        val result: MowerEngineInput = MowerEngineIOParser.parseInput(input)
        // Assert
        assertEquals(2, result.mowerInitDatas.size)
        assertEquals(Direction.EAST, result.mowerInitDatas[1].direction)
        assertEquals(3, result.mowerInitDatas[1].coordinates.x)
        assertEquals(3, result.mowerInitDatas[1].coordinates.y)
        assertEquals(
            listOf(
                MowerAction.MOVE_FORWARD, MowerAction.MOVE_FORWARD, MowerAction.ROTATE_RIGHT, MowerAction.MOVE_FORWARD,
                MowerAction.MOVE_FORWARD, MowerAction.ROTATE_RIGHT, MowerAction.MOVE_FORWARD, MowerAction.ROTATE_RIGHT, MowerAction.ROTATE_RIGHT, MowerAction.MOVE_FORWARD
            ),
            result.mowerInitDatas[1].actions
        )
    }

    @Test
    fun `parseInput should throw on invalid direction`() {
        // Arrange
        val input = """
            5 5
            1 2 X
            LMLMLMLMM
        """.trimIndent()
        // Act & Assert
        val ex = assertThrows<IllegalArgumentException> {
            MowerEngineIOParser.parseInput(input)
        }
        assertTrue(ex.message!!.contains("Invalid direction"))
    }

    @Test
    fun `parseInput should throw on invalid action`() {
        // Arrange
        val input = """
            5 5
            1 2 N
            LMLMLMLMX
        """.trimIndent()
        // Act & Assert
        val ex = assertThrows<IllegalArgumentException> {
            MowerEngineIOParser.parseInput(input)
        }
        assertTrue(ex.message!!.contains("Invalid mower action"))
    }

    @Test
    fun `parseInput should throw on invalid format`() {
        // Arrange
        val input = """
            5 5
        """.trimIndent() // Missing actions
        // Act & Assert
        val ex = assertThrows<IllegalArgumentException> {
            MowerEngineIOParser.parseInput(input)
        }
        assertTrue(ex.message!!.contains("Invalid input format"))
    }

    @Test
    fun `formatOutput should format mower results as expected`() {
        // Arrange
        val mowerResults = listOf(
            MowerResult(Coordinates(1, 2), Direction.NORTH),
            MowerResult(Coordinates(3, 3), Direction.EAST),
            MowerResult(Coordinates(0, 0), Direction.WEST)
        )
        val engineResult = MowerEngineResult(mowerResults)
        val expected = listOf("1 2 N", "3 3 E", "0 0 W")

        // Act
        val actual: List<String> = MowerEngineIOParser.formatOutput(engineResult)

        // Assert
        assertEquals(expected, actual)
    }
}

