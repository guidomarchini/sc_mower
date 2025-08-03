package com.seatcode.mowerengine.service.utils

import com.seatcode.mowerengine.service.MowerEngineContract
import com.seatcode.mowerengine.service.MowerEngineInput
import com.seatcode.mowerengine.service.MowerEngineResult
import org.slf4j.LoggerFactory

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
