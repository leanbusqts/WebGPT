package dev.bulean.webgpt.domain

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class DefaultPromptBuilderTest : BasePlatformTestCase() {
    fun testBuildExplainPromptContainsCode() {
        // Given
        val builder = DefaultPromptBuilder()
        val code = "fun test() = 1"

        // When
        val prompt = builder.buildPrompt(PromptType.EXPLAIN, code)

        // Then
        assertTrue(prompt.contains(code))
        assertTrue(prompt.contains("# Explain this code in detail."))
    }

    fun testBuildFixPromptContainsCode() {
        // Given
        val builder = DefaultPromptBuilder()
        val code = "fun test() = 2"

        // When
        val prompt = builder.buildPrompt(PromptType.FIX, code)

        // Then
        assertTrue(prompt.contains(code))
        assertTrue(prompt.contains("# Fix and optimize this code, and suggest architecture improvements."))
    }
}
