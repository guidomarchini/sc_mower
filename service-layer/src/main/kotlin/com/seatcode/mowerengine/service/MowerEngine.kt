package com.seatcode.mowerengine.service

import com.seatcode.mowerengine.model.Mower
import com.seatcode.mowerengine.model.MowerAction
import com.seatcode.mowerengine.model.Plateau
import org.slf4j.LoggerFactory

sealed interface MowerEngineContract {
    fun execute(input: MowerEngineInput): MowerEngineResult
}

class MowerEngine(
    handlers: List<MowerActionHandler>
) : MowerEngineContract {
    private val handlerMap: Map<MowerAction, MowerActionHandler> = handlers.associateBy { it.action }
    private val logger = LoggerFactory.getLogger(MowerEngine::class.java)

    override fun execute(input: MowerEngineInput): MowerEngineResult {
        MowerEngineInputValidator.validate(input)
        val plateau: Plateau = Plateau(input.plateauMaxX, input.plateauMaxY)
        val mowersWithActions: List<MowerWithActions> = input.mowerInitDatas.map { initData ->
            val mower: Mower = Mower(initData.coordinates, initData.direction)
            plateau.addMower(mower)
            MowerWithActions(mower, initData.actions)
        }

        logger.debug("Parsed all mowers and actions: {}", mowersWithActions)
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

class LoggingMowerEngineDecorator(
    private val delegate: MowerEngineContract
) : MowerEngineContract by delegate {
    private val logger = LoggerFactory.getLogger(LoggingMowerEngineDecorator::class.java)

    override fun execute(input: MowerEngineInput): MowerEngineResult {
        logger.info("Starting MowerEngine execution: plateau=(${input.plateauMaxX}, ${input.plateauMaxY}), mowers=${input.mowerInitDatas.size}")
        val startTime = System.currentTimeMillis()
        return try {
            val result = delegate.execute(input)
            val elapsed = System.currentTimeMillis() - startTime
            logger.debug("MowerEngine execution finished. Results: {}. Time: {}ms", result.mowerResults, elapsed)
            result
        } catch (ex: IllegalArgumentException) {
            logger.warn("Validation failed: {}", ex.message)
            throw ex
        } catch (ex: Exception) {
            logger.error("Unexpected error: {}", ex.message, ex)
            throw ex
        }
    }
}
