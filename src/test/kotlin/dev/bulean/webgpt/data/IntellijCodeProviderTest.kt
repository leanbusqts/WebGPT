package dev.bulean.webgpt.data

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Assert.assertEquals

class IntellijCodeProviderTest : BasePlatformTestCase() {

    fun testReturnsSelectedTextWhenPresent() {
        // Given
        myFixture.configureByText(
            "Sample.kt",
            "fun main() {\n    val answer = 42\n}\n"
        )
        val documentText = myFixture.editor.document.charsSequence.toString()
        val selectionStart = documentText.indexOf("val")
        val selectionEnd = documentText.indexOf("42") + "42".length
        myFixture.editor.selectionModel.setSelection(selectionStart, selectionEnd)
        val provider = IntellijCodeProvider(project)

        // When
        val text = provider.getSelectedOrAllText()

        // Then
        assertEquals("val answer = 42", text)
    }

    fun testReturnsEntireFileWhenNoSelection() {
        // Given
        val fileText = "fun main() {\n    val answer = 42\n}\n"
        myFixture.configureByText("Sample.kt", fileText)
        myFixture.editor.selectionModel.removeSelection()
        val provider = IntellijCodeProvider(project)

        // When
        val text = provider.getSelectedOrAllText()

        // Then
        assertEquals(fileText, text)
    }
}
