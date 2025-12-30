package dev.bulean.webgpt.data

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class EditorUtilsTest : BasePlatformTestCase() {
    fun testGetSelectedTextOrAllReturnsSelection() {
        // Given
        myFixture.configureByText("Test.kt", "fun test() = 1")
        myFixture.editor.selectionModel.setSelection(0, 3)

        // When
        val result = EditorUtils.getSelectedTextOrAll(project)

        // Then
        assertEquals("fun", result)
    }

    fun testGetSelectedTextOrAllReturnsDocumentWhenNoSelection() {
        // Given
        myFixture.configureByText("Test.kt", "fun test() = 1")
        myFixture.editor.selectionModel.removeSelection()

        // When
        val result = EditorUtils.getSelectedTextOrAll(project)

        // Then
        assertEquals("fun test() = 1", result)
    }
}
