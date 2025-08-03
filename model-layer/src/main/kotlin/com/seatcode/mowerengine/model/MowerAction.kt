package com.seatcode.mowerengine.model

sealed interface MowerAction {
    companion object {
        val ROTATE_LEFT: MowerAction = RotateLeft
        val ROTATE_RIGHT: MowerAction = RotateRight
        val MOVE_FORWARD: MowerAction = MoveForward
    }
}

object RotateLeft : MowerAction
object RotateRight : MowerAction
object MoveForward : MowerAction
