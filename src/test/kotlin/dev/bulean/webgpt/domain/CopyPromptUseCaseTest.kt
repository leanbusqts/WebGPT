package dev.bulean.webgpt.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CopyPromptUseCaseTest {

    @Test
    fun copiesPromptWhenCodeExists() {
        // Given
        val clipboard = RecordingClipboardGateway()
        val messages = RecordingMessageGateway()
        val useCase = CopyPromptUseCase(
            codeProvider = FakeCodeProvider("val x = 1"),
            promptBuilder = FakePromptBuilder(),
            clipboardGateway = clipboard,
            messageGateway = messages
        )

        // When
        useCase.execute(PromptType.EXPLAIN)

        // Then
        assertEquals("EXPLAIN:val x = 1", clipboard.lastCopied)
        assertEquals("Prompt with selected code copied to clipboard", messages.lastMessage)
    }

    @Test
    fun showsMessageWhenNoCode() {
        // Given
        val clipboard = RecordingClipboardGateway()
        val messages = RecordingMessageGateway()
        val useCase = CopyPromptUseCase(
            codeProvider = FakeCodeProvider(null),
            promptBuilder = FakePromptBuilder(),
            clipboardGateway = clipboard,
            messageGateway = messages
        )

        // When
        useCase.execute(PromptType.FIX)

        // Then
        assertNull(clipboard.lastCopied)
        assertEquals("No code selected", messages.lastMessage)
    }

    private class FakeCodeProvider(private val text: String?) : CodeProvider {
        override fun getSelectedOrAllText(): String? = text
    }

    private class FakePromptBuilder : PromptBuilder {
        override fun buildPrompt(type: PromptType, code: String?): String {
            return "${type.name}:$code"
        }
    }

    private class RecordingClipboardGateway : ClipboardGateway {
        var lastCopied: String? = null
        override fun copyText(text: String) {
            lastCopied = text
        }
    }

    private class RecordingMessageGateway : MessageGateway {
        var lastMessage: String? = null
        override fun showInfo(message: String) {
            lastMessage = message
        }
    }
}
