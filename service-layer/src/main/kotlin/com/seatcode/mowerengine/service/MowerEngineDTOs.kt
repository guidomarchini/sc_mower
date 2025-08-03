package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.*

data class MowerEngineInput(
    val plateauMaxX: Int,
    val plateauMaxY: Int,
    val mowerInitDatas: List<MowerInitData>
)

data class MowerInitData(
    val coordinates: Coordinates,
    val direction: Direction,
    val actions: List<MowerAction>
)

data class MowerEngineResult(
    val mowerResults: List<MowerResult>
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

