package com.seatcode.mowerengine.model

class Mower(
    private var coordinates: Coordinates,
    private var direction: Direction
) {
    fun rotateLeft() {
        this.direction = this.direction.rotateLeft()
    }

    fun rotateRight() {
        this.direction = this.direction.rotateRight()
    }

    fun calculateNextCoordinates(): Coordinates {
        return this.direction.moveForward(coordinates)
    }

    fun updateCoordinates(newCoordinates: Coordinates) {
        this.coordinates = newCoordinates
    }

    fun getCurrentCoordinates(): Coordinates = this.coordinates
    fun getCurrentDirection(): Direction = this.direction
}

