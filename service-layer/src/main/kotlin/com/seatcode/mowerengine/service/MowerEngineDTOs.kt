package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.*

data class MowerInitData(
    val coordinates: Coordinates,
    val direction: Direction,
    val actions: List<MowerAction>
)

data class MowerEngineInput(
    val plateauMaxX: Int,
    val plateauMaxY: Int,
    val mowers: List<MowerInitData>
)

data class MowerResult(
    val coordinates: Coordinates,
    val direction: Direction
) {
    companion object {
        fun fromMower(mower: Mower): MowerResult {
            return MowerResult(
                coordinates = mower.getCurrentCoordinates(),
                direction = mower.getCurrentDirection()
            )
        }
    }
}

data class MowerEngineResult(
    val mowers: List<MowerResult>
)

