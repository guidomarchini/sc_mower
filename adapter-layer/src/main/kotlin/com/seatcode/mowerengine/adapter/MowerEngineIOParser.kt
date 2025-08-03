package com.seatcode.mowerengine.adapter

import com.seatcode.mowerengine.model.*
import com.seatcode.mowerengine.service.MowerEngineInput
import com.seatcode.mowerengine.service.MowerEngineResult
import com.seatcode.mowerengine.service.MowerInitData

object MowerEngineIOParser {
    private val directionMap: Map<String, Direction> = mapOf(
        "N" to Direction.NORTH,
        "E" to Direction.EAST,
        "S" to Direction.SOUTH,
        "W" to Direction.WEST
    )
    private val reverseDirectionMap: Map<Direction, String> = directionMap.entries.associate { (k, v) -> v to k }

    fun parseInput(input: String): MowerEngineInput {
        val lines: List<String> = input.lines().filter { it.isNotBlank() }
        require(lines.size >= 3 && lines.size % 2 == 1) {
            "Invalid input format. Input string should have the plateau top right corner, followed by at least one mower's initial position and actions."
        }

        val (maxX: Int, maxY: Int) = lines[0].split(" ").map { it.toInt() }
        val mowers: MutableList<MowerInitData> = mutableListOf()
        var i: Int = 1
        while (i < lines.size) {
            mowers.add(parseMowerInitData(lines, i))
            i += 2
        }
        return MowerEngineInput(maxX, maxY, mowers)
    }

    private fun parseMowerInitData(
        lines: List<String>,
        i: Int
    ): MowerInitData {
        val (x: String, y: String, dir: String) = lines[i].split(" ")
        val coordinates: Coordinates = Coordinates(x.toInt(), y.toInt())
        val direction: Direction = parseDirection(dir)
        val actions: List<MowerAction> = lines[i + 1].map { parseMowerAction(it) }
        return MowerInitData(coordinates, direction, actions)
    }

    private fun parseDirection(dir: String): Direction {
        return directionMap[dir.uppercase()] ?: throw IllegalArgumentException("Invalid direction: $dir")
    }

    private fun parseMowerAction(char: Char): MowerAction {
        return when (char.uppercaseChar()) {
            'L' -> MowerAction.ROTATE_LEFT
            'R' -> MowerAction.ROTATE_RIGHT
            'M' -> MowerAction.MOVE_FORWARD
            else -> throw IllegalArgumentException("Invalid mower action: $char")
        }
    }

    fun formatOutput(mowerEngineResult: MowerEngineResult): List<String> {
        return mowerEngineResult.mowerResults.map {
            "${it.coordinates.x} ${it.coordinates.y} ${formatDirection(it.direction)}"
        }
    }

    fun formatDirection(direction: Direction): String {
        return reverseDirectionMap[direction] ?: throw IllegalArgumentException("Invalid direction: $direction")
    }
}

