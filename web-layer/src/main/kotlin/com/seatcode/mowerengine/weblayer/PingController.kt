package com.seatcode.mowerengine.weblayer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class PingResponse(val message: String)

@RestController
class PingController {
    @GetMapping("/ping")
    fun ping(): PingResponse = PingResponse("pong")
}
