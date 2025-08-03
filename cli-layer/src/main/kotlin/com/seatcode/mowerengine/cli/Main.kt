package com.seatcode.mowerengine.cli

import com.seatcode.mowerengine.adapter.MowerEngineIOParser
import com.seatcode.mowerengine.service.*

fun main() {
    println("Enter the top right corner of the plateau (e.g., 5 5):")
    val plateau: String = readln().trim()

    println("Enter the position and direction of mower1 (e.g., 1 2 N):")
    val mower1: String = readln().trim()
    println("Enter the actions for mower1 (e.g., LMLMLMLMM):")
    val actions1: String = readln().trim()

    println("Enter the position and direction of mower2 (e.g., 3 3 E):")
    val mower2: String = readln().trim()
    println("Enter the actions for mower2 (e.g., MMRMMRMRRM):")
    val actions2: String = readln().trim()

    val input: String = listOf(plateau, mower1, actions1, mower2, actions2).joinToString("\n")

    val engine: MowerEngineContract = MowerEngine(
        listOf(RotateLeftHandler(), RotateRightHandler(), MoveForwardHandler())
    )
    val loggingEngine: MowerEngineContract = LoggingMowerEngineDecorator(engine)

    val engineInput = MowerEngineIOParser.parseInput(input)
    val result: MowerEngineResult = loggingEngine.execute(engineInput)
    val output = MowerEngineIOParser.formatOutput(result)

    println("\nResults:")
    output.forEach { println(it) }
}
