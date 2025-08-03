package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.*

sealed class MowerActionHandler {
    abstract val action: MowerAction
    abstract fun handle(mower: Mower, plateau: Plateau): Unit
}

class RotateLeftHandler : MowerActionHandler() {
    override val action: MowerAction = RotateLeft
    override fun handle(mower: Mower, plateau: Plateau): Unit = mower.rotateLeft()
}

class RotateRightHandler : MowerActionHandler() {
    override val action: MowerAction = RotateRight
    override fun handle(mower: Mower, plateau: Plateau): Unit = mower.rotateRight()
}

class MoveForwardHandler : MowerActionHandler() {
    override val action: MowerAction = MoveForward

    override fun handle(mower: Mower, plateau: Plateau): Unit {
        val nextCoordinates: Coordinates = mower.calculateNextCoordinates()
        if (plateau.areValidCoordinates(nextCoordinates)) {
            mower.updateCoordinates(nextCoordinates)
        }
    }
}
