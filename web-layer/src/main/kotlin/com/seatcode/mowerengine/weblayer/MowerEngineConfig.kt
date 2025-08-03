package com.seatcode.mowerengine.weblayer

import com.seatcode.mowerengine.service.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MowerEngineConfig {
    @Bean
    open fun rotateLeftHandler(): RotateLeftHandler = RotateLeftHandler()

    @Bean
    open fun rotateRightHandler(): RotateRightHandler = RotateRightHandler()

    @Bean
    open fun moveForwardHandler(): MoveForwardHandler = MoveForwardHandler()

    @Bean
    open fun mowerEngine(
        rotateLeftHandler: RotateLeftHandler,
        rotateRightHandler: RotateRightHandler,
        moveForwardHandler: MoveForwardHandler
    ): MowerEngineContract = LoggingMowerEngineDecorator(MowerEngine(
        listOf(rotateLeftHandler, rotateRightHandler, moveForwardHandler)
    ))
}

