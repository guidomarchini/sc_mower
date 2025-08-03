package com.seatcode.mowerengine.model

sealed interface MowerAction {
    companion object {
        val ROTATE_LEFT: MowerAction = RotateLeft
        val ROTATE_RIGHT: MowerAction = RotateRight
        val MOVE_FORWARD: MowerAction = MoveForward
    }
}

object RotateLeft : MowerAction {
    override fun toString(): String = "Rotate Left"
}
object RotateRight : MowerAction {
    override fun toString(): String = "Rotate Right"
}
object MoveForward : MowerAction {
    override fun toString(): String = "Move Forward"
}
