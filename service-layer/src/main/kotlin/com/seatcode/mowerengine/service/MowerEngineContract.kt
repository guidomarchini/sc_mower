package com.seatcode.mowerengine.service

fun interface MowerEngineContract {
    fun execute(input: MowerEngineInput): MowerEngineResult
}