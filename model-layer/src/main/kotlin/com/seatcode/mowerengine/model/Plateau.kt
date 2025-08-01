package com.seatcode.mowerengine.model

class Plateau(
    val maxX: Int,
    val maxY: Int
) {
    private val mowers: MutableList<Mower> = mutableListOf()

    fun addMower(mower: Mower): Boolean {
        val coordinates: Coordinates = mower.getCurrentCoordinates()
        if (areValidCoordinates(coordinates) && !isOccupied(coordinates)) {
            mowers.add(mower)
            return true
        }
        return false
    }

    fun removeMower(mower: Mower): Boolean {
        return mowers.remove(mower)
    }

    fun getMowers(): List<Mower> {
        return mowers.toList()
    }

    fun isOccupied(coordinates: Coordinates): Boolean {
        return mowers.any { it.getCurrentCoordinates() == coordinates }
    }

    fun areValidCoordinates(coordinates: Coordinates): Boolean {
        return coordinates.x >= 0
            && coordinates.x <= this.maxX
            && coordinates.y >= 0
            && coordinates.y <= this.maxY
            && !isOccupied(coordinates)
    }
}
