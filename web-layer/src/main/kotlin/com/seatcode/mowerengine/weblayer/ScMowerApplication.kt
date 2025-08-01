package com.seatcode.mowerengine.weblayer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.view.RedirectView

@SpringBootApplication
open class ScMowerApplication

fun main(args: Array<String>) {
    runApplication<ScMowerApplication>(*args)
}

@Controller
class RootRedirectController {
    @GetMapping("/")
    fun redirectToSwagger(): RedirectView = RedirectView("/swagger-ui/index.html")
}
