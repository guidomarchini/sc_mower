package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.Mower
import com.seatcode.mowerengine.model.Plateau
import com.seatcode.mowerengine.service.utils.MowerWithActions

class ActionByActionMowerEngine(
    handlers: List<MowerActionHandler>
) : AbstractMowerEngine(handlers) {

    override fun executeActions(
        mowerWithActions: List<MowerWithActions>,
        plateau: Plateau
    ): List<Mower> {
        var mowersNeedToMove: Boolean = true
        var currentAction: Int = 0

        while (mowersNeedToMove) {
            mowersNeedToMove = false

            for ((mower, actions) in mowerWithActions) {
                if (currentAction < actions.size) {
                    val action = actions[currentAction]
                    handleAction(mower, action, plateau)
                    mowersNeedToMove = true
                } // return the mowers in finishing order?
            }

            currentAction++
        }

        return mowerWithActions.map { it.mower }
    }
}