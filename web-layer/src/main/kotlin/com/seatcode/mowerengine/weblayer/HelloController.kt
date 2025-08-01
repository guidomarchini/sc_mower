package com.seatcode.mowerengine.weblayer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class HelloResponse(val message: String)

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello() = HelloResponse("Hello, Spring Boot with Kotlin!")
}
