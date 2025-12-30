package dev.bulean.webgpt.core

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class WebGPTConstantsTest : BasePlatformTestCase() {
    fun testChatOpenAiUrl() {
        // Given
        val expected = "https://chat.openai.com"

        // When
        val actual = WebGPTConstants.CHAT_OPENAI_URL

        // Then
        assertEquals(expected, actual)
    }
}
