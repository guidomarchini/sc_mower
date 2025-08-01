package com.seatcode.mowerengine.model

sealed class MowerAction

object RotateLeft : MowerAction()
object RotateRight : MowerAction()
object MoveForward : MowerAction()
