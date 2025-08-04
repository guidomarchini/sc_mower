package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.Mower
import com.seatcode.mowerengine.model.Plateau
import com.seatcode.mowerengine.service.handler.MowerActionHandler
import com.seatcode.mowerengine.service.utils.MowerWithActions

class SequentialMowerEngine(
    handlers: List<MowerActionHandler>
) : AbstractMowerEngine(handlers) {

    override fun executeActions(
        mowerWithActions: List<MowerWithActions>,
        plateau: Plateau
    ): List<Mower> {
        return mowerWithActions.map { (mower, actions) ->
            actions.forEach { action -> handleAction(mower, action, plateau) }
            mower
        }
    }
}

