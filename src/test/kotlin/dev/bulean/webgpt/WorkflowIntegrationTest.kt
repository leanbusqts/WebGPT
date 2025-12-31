package dev.bulean.webgpt

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import dev.bulean.webgpt.data.IntellijCodeProvider
import dev.bulean.webgpt.domain.ClipboardGateway
import dev.bulean.webgpt.domain.CopyPromptUseCase
import dev.bulean.webgpt.domain.MessageGateway
import dev.bulean.webgpt.domain.PromptBuilder
import dev.bulean.webgpt.domain.PromptType
import org.junit.Assert.assertEquals

class WorkflowIntegrationTest : BasePlatformTestCase() {

    fun testCopiesBuiltPromptFromSelectedEditorText() {
        // Given
        myFixture.configureByText(
            "Sample.kt",
            "fun main() {\n    val value = 7\n}\n"
        )
        val documentText = myFixture.editor.document.charsSequence.toString()
        val selected = "val value = 7"
        val selectionStart = documentText.indexOf(selected)
        myFixture.editor.selectionModel.setSelection(selectionStart, selectionStart + selected.length)
        val clipboard = RecordingClipboardGateway()
        val messages = RecordingMessageGateway()
        val useCase = CopyPromptUseCase(
            codeProvider = IntellijCodeProvider(project),
            promptBuilder = InlinePromptBuilder(),
            clipboardGateway = clipboard,
            messageGateway = messages
        )

        // When
        useCase.execute(PromptType.EXPLAIN)

        // Then
        assertEquals("Built: $selected", clipboard.lastCopied)
        assertEquals("Prompt with selected code copied to clipboard", messages.lastMessage)
    }

    private class InlinePromptBuilder : PromptBuilder {
        override fun buildPrompt(type: PromptType, code: String?): String {
            return "Built: $code"
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
