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
    override fun rotateLeft() = Direction.WEST
    override fun rotateRight() = Direction.EAST
    override fun moveForward(position: Position) = position.copy(y = position.y + 1)
}

object South : Direction() {
    override fun rotateLeft() = Direction.EAST
    override fun rotateRight() = Direction.WEST
    override fun moveForward(position: Position) = position.copy(y = position.y - 1)
}

object East : Direction() {
    override fun rotateLeft() = Direction.NORTH
    override fun rotateRight() = Direction.SOUTH
    override fun moveForward(position: Position) = position.copy(x = position.x + 1)
}

object West : Direction() {
    override fun rotateLeft() = Direction.SOUTH
    override fun rotateRight() = Direction.NORTH
    override fun moveForward(position: Position) = position.copy(x = position.x - 1)
}

