package dev.bulean.webgpt.domain

import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultPromptBuilderTest {

    private val builder = DefaultPromptBuilder()

    @Test
    fun explainPromptContainsCodeAndHeaders() {
        // Given
        val code = "fun main() = Unit"

        // When
        val prompt = builder.buildPrompt(PromptType.EXPLAIN, code)

        // Then
        assertTrue(prompt.contains("# Explain this code in detail."))
        assertTrue(prompt.contains("```"))
        assertTrue(prompt.contains(code))
    }

    @Test
    fun fixPromptContainsCodeAndInstructions() {
        // Given
        val code = "val x = 1"

        // When
        val prompt = builder.buildPrompt(PromptType.FIX, code)

        // Then
        assertTrue(prompt.contains("# Fix and optimize this code, and suggest architecture improvements."))
        assertTrue(prompt.contains("```"))
        assertTrue(prompt.contains(code))
    }
}
