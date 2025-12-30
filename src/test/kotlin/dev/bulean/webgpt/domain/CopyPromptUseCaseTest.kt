package dev.bulean.webgpt.domain

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class CopyPromptUseCaseTest : BasePlatformTestCase() {
    fun testExecuteCopiesPrompt() {
        // Given
        val codeProvider = FakeCodeProvider("fun test() = 1")
        val promptBuilder = DefaultPromptBuilder()
        val clipboard = FakeClipboardGateway()
        val messages = FakeMessageGateway()
        val useCase = CopyPromptUseCase(codeProvider, promptBuilder, clipboard, messages)

        // When
        useCase.execute(PromptType.EXPLAIN)

        // Then
        assertNotNull(clipboard.copiedText)
        assertTrue(clipboard.copiedText!!.contains("fun test() = 1"))
        assertEquals("Prompt with selected code copied to clipboard", messages.lastMessage)
    }

    fun testExecuteWithBlankCodeShowsMessage() {
        // Given
        val codeProvider = FakeCodeProvider("   ")
        val promptBuilder = DefaultPromptBuilder()
        val clipboard = FakeClipboardGateway()
        val messages = FakeMessageGateway()
        val useCase = CopyPromptUseCase(codeProvider, promptBuilder, clipboard, messages)

        // When
        useCase.execute(PromptType.FIX)

        // Then
        assertNull(clipboard.copiedText)
        assertEquals("No code selected", messages.lastMessage)
    }

    private class FakeCodeProvider(private val code: String?) : CodeProvider {
        override fun getSelectedOrAllText(): String? = code
    }

    private class FakeClipboardGateway : ClipboardGateway {
        var copiedText: String? = null
        override fun copyText(text: String) {
            copiedText = text
        }
    }

    private class FakeMessageGateway : MessageGateway {
        var lastMessage: String? = null
        override fun showInfo(message: String) {
            lastMessage = message
        }
    }
}
