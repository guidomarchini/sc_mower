package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.Mower
import com.seatcode.mowerengine.model.Plateau
import com.seatcode.mowerengine.service.utils.MowerWithActions
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

class ConcurrentMowerEngine(
    handlers: List<MowerActionHandler>
): AbstractMowerEngine(handlers) {

    override fun executeActions(
        mowerWithActions: List<MowerWithActions>,
        plateau: Plateau
    ): List<Mower> {
        return runBlocking {
            mowerWithActions.map { (mower, actions) ->
                async {
                    actions.forEach { action -> handleAction(mower, action, plateau) }
                    mower
                }
            }.awaitAll()
        }
    }
}
