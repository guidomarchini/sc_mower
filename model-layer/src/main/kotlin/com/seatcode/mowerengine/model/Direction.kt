package com.seatcode.mowerengine.model

sealed interface Direction {
    fun rotateLeft(): Direction
    fun rotateRight(): Direction
    fun moveForward(coordinates: Coordinates): Coordinates

    companion object {
        val NORTH: Direction = North
        val SOUTH: Direction = South
        val EAST: Direction = East
        val WEST: Direction = West
    }
}

object North : Direction {
    override fun rotateLeft(): Direction = Direction.WEST
    override fun rotateRight(): Direction = Direction.EAST
    override fun moveForward(coordinates: Coordinates): Coordinates = coordinates.copy(y = coordinates.y + 1)
}

object South : Direction {
    override fun rotateLeft(): Direction = Direction.EAST
    override fun rotateRight(): Direction = Direction.WEST
    override fun moveForward(coordinates: Coordinates): Coordinates = coordinates.copy(y = coordinates.y - 1)
}

object East : Direction {
    override fun rotateLeft(): Direction = Direction.NORTH
    override fun rotateRight(): Direction = Direction.SOUTH
    override fun moveForward(coordinates: Coordinates): Coordinates = coordinates.copy(x = coordinates.x + 1)
}

object West : Direction {
    override fun rotateLeft(): Direction = Direction.SOUTH
    override fun rotateRight(): Direction = Direction.NORTH
    override fun moveForward(coordinates: Coordinates): Coordinates = coordinates.copy(x = coordinates.x - 1)
}
