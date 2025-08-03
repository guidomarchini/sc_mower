package com.seatcode.mowerengine.weblayer

import com.seatcode.mowerengine.service.MowerEngine
import com.seatcode.mowerengine.service.MowerEngineInput
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Mower Engine", description = "Endpoints for executing mower engine commands")
@RestController
class MowerEngineV1Controller @Autowired constructor(
    private val mowerEngine: MowerEngine
) {
    @Operation(summary = "Execute mower engine commands", description = "Receives a multiline string with plateau and mower instructions, returns final mower positions.")
    @PostMapping("/api/v1/execute")
    fun executeMowerEngine(@RequestBody input: String): List<String> {
        val engineInput: MowerEngineInput = MowerEngineIOParser.parseInput(input)
        val result = mowerEngine.execute(engineInput)
        return MowerEngineIOParser.formatOutput(result)
    }
}
