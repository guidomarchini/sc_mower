package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.Mower
import com.seatcode.mowerengine.model.MowerAction
import com.seatcode.mowerengine.model.Plateau
import com.seatcode.mowerengine.service.handler.MowerActionHandler
import com.seatcode.mowerengine.service.utils.MowerWithActions
import org.slf4j.LoggerFactory

abstract class AbstractMowerEngine(
    handlers: List<MowerActionHandler>
): MowerEngineContract {

    protected val handlerMap: Map<MowerAction, MowerActionHandler> = handlers.associateBy { it.action }
    private val logger = LoggerFactory.getLogger(SequentialMowerEngine::class.java)

    override fun execute(input: MowerEngineInput): MowerEngineResult {
        MowerEngineInputValidator.validate(input)
        val plateau: Plateau = Plateau(input.plateauMaxX, input.plateauMaxY)
        val mowerWithActions: List<MowerWithActions> = input.mowerInitDatas.map { initData ->
            val mower: Mower = Mower(initData.coordinates, initData.direction)
            plateau.addMower(mower)
            MowerWithActions(mower, initData.actions)
        }
        logger.debug("Parsed all mowers and actions: {}", mowerWithActions)

        return executeActions(
            mowerWithActions,
            plateau
        ).map { mower -> MowerResult.fromMower(mower) }
        .let { MowerEngineResult(it) }
    }

    fun handleAction(
        mower: Mower,
        action: MowerAction,
        plateau: Plateau
    ) {
        handlerMap[action]
            ?.also { it.handle(mower, plateau) }
            ?: error("No handler for action: $action")
    }

    abstract fun executeActions(
        mowerWithActions: List<MowerWithActions>,
        plateau: Plateau
    ): List<Mower>
}