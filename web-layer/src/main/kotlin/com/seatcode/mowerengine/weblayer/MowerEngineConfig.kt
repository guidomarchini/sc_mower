package com.seatcode.mowerengine.weblayer

import com.seatcode.mowerengine.service.MoveForwardHandler
import com.seatcode.mowerengine.service.MowerEngine
import com.seatcode.mowerengine.service.RotateLeftHandler
import com.seatcode.mowerengine.service.RotateRightHandler
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
    ): MowerEngine = MowerEngine(
        listOf(rotateLeftHandler, rotateRightHandler, moveForwardHandler)
    )
}

