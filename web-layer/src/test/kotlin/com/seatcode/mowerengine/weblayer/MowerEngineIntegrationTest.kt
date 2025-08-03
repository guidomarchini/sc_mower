package com.seatcode.mowerengine.weblayer

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MowerEngineIntegrationTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should return mower results for valid input`() {
        // Arrange
        val input = """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent()
        val expected = listOf("1 3 N", "5 1 E")

        // Act
        val result = mockMvc.perform(
            post("/api/v1/execute")
                .contentType(MediaType.TEXT_PLAIN)
                .content(input)
        )
        // Assert
        result
            .andExpect(status().isOk)
            .andExpect(content().json(ObjectMapper().writeValueAsString(expected)))
    }

    @Test
    fun `should return 400 Bad Request for invalid direction`() {
        // Arrange
        val input = """
            5 5
            1 2 X
            LMLMLMLMM
        """.trimIndent()
        // Act
        val result = mockMvc.perform(
            post("/api/v1/execute")
                .contentType(MediaType.TEXT_PLAIN)
                .content(input)
        )
        // Assert
        result.andExpect(status().isBadRequest)
    }

    @Test
    fun `should return 400 Bad Request for invalid action`() {
        // Arrange
        val input = """
            5 5
            1 2 N
            LMLMLMLZZ
        """.trimIndent()
        // Act
        val result = mockMvc.perform(
            post("/api/v1/execute")
                .contentType(MediaType.TEXT_PLAIN)
                .content(input)
        )
        // Assert
        result.andExpect(status().isBadRequest)
    }

    @Test
    fun `should return 400 Bad Request for mower out of plateau`() {
        // Arrange
        val input = """
            5 5
            6 2 N
            LMLMLMLMM
        """.trimIndent()
        // Act
        val result = mockMvc.perform(
            post("/api/v1/execute")
                .contentType(MediaType.TEXT_PLAIN)
                .content(input)
        )
        // Assert
        result.andExpect(status().isBadRequest)
    }

    @Test
    fun `should return 400 Bad Request for overlapping mowers`() {
        // Arrange
        val input = """
            5 5
            1 2 N
            LMLMLMLMM
            1 2 E
            MMRMMRMRRM
        """.trimIndent()
        // Act
        val result = mockMvc.perform(
            post("/api/v1/execute")
                .contentType(MediaType.TEXT_PLAIN)
                .content(input)
        )
        // Assert
        result.andExpect(status().isBadRequest)
    }
}
