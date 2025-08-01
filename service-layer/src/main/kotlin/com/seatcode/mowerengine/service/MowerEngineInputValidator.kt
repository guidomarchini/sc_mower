package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.Coordinates

object MowerEngineInputValidator {
    fun validate(input: MowerEngineInput): Unit {
        require(input.plateauMaxX >= 0) { "Plateau maxX must be non-negative" }
        require(input.plateauMaxY >= 0) { "Plateau maxY must be non-negative" }

        val seenCoordinates: MutableSet<Coordinates> = mutableSetOf()
        input.mowers.forEach { initData ->
            require(initData.coordinates.x >= 0 && initData.coordinates.x <= input.plateauMaxX) {
                "Mower x coordinate out of plateau bounds: ${initData.coordinates.x}"
            }
            require(initData.coordinates.y >= 0 && initData.coordinates.y <= input.plateauMaxY) {
                "Mower y coordinate out of plateau bounds: ${initData.coordinates.y}"
            }
            require(seenCoordinates.add(initData.coordinates)) {
                "Two mowers cannot occupy the same initial position: ${initData.coordinates}"
            }
        }
    }
}

