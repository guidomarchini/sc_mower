package com.seatcode.mowerengine.model

sealed interface class Direction {
    abstract fun rotateLeft(): Direction
    abstract fun rotateRight(): Direction
    abstract fun moveForward(position: Position): Position

    companion object {
        val NORTH: Direction = North
        val SOUTH: Direction = South
        val EAST: Direction = East
        val WEST: Direction = West
    }
}

object North : Direction() {
    override fun rotateLeft(): Direction = Direction.WEST
    override fun rotateRight(): Direction = Direction.EAST
    override fun moveForward(position: Position): Position = position.copy(y = position.y + 1)
}

object South : Direction() {
    override fun rotateLeft(): Direction = Direction.EAST
    override fun rotateRight(): Direction = Direction.WEST
    override fun moveForward(position: Position): Position = position.copy(y = position.y - 1)
}

object East : Direction() {
    override fun rotateLeft(): Direction = Direction.NORTH
    override fun rotateRight(): Direction = Direction.SOUTH
    override fun moveForward(position: Position): Position = position.copy(x = position.x + 1)
}

object West : Direction() {
    override fun rotateLeft(): Direction = Direction.SOUTH
    override fun rotateRight(): Direction = Direction.NORTH
    override fun moveForward(position: Position): Position = position.copy(x = position.x - 1)
}
