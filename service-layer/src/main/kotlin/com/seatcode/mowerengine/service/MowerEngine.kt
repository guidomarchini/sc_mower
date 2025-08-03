package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.Mower
import com.seatcode.mowerengine.model.MowerAction
import com.seatcode.mowerengine.model.Plateau

class MowerEngine(
    handlers: List<MowerActionHandler>
) {
    private val handlerMap: Map<MowerAction, MowerActionHandler> = handlers.associateBy { it.action }

    fun execute(input: MowerEngineInput): MowerEngineResult {
        MowerEngineInputValidator.validate(input)

        val plateau: Plateau = Plateau(input.plateauMaxX, input.plateauMaxY)

        val mowersWithActions: List<MowerWithActions> = input.mowerInitDatas.map { initData ->
            val mower: Mower = Mower(initData.coordinates, initData.direction)
            plateau.addMower(mower)
            MowerWithActions(mower, initData.actions)
        }

        return mowersWithActions.map { (mower, actions) ->
            this.executeActions(mower, actions, plateau)
            MowerResult.fromMower(mower)
        }.let { MowerEngineResult(it) }
    }

    private fun executeActions(mower: Mower, actions: List<MowerAction>, plateau: Plateau): Unit {
        actions.forEach { action ->
            handlerMap[action]
                ?.also { it.handle(mower, plateau) }
                ?: error("No handler for action: $action")
        }
    }

    data class MowerWithActions(val mower: Mower, val actions: List<MowerAction>)
}
