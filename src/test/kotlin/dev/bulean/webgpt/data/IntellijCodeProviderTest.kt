package dev.bulean.webgpt.data

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class IntellijCodeProviderTest : BasePlatformTestCase() {
    fun testGetSelectedOrAllTextUsesEditorSelection() {
        // Given
        myFixture.configureByText("Test.kt", "fun test() = 1")
        myFixture.editor.selectionModel.setSelection(4, 8)
        val provider = IntellijCodeProvider(project)

        // When
        val result = provider.getSelectedOrAllText()

        // Then
        assertEquals("test", result)
    }
}
