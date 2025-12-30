package dev.bulean.webgpt.presentation

import com.intellij.openapi.wm.ToolWindowAnchor
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.ui.jcef.JBCefApp
import org.junit.Assume.assumeTrue
import java.awt.GraphicsEnvironment

class WebGPTToolWindowE2ETest : BasePlatformTestCase() {
    fun testToolWindowContentIsCreated() {
        // Given
        assumeTrue(!GraphicsEnvironment.isHeadless())
        assumeTrue(JBCefApp.isSupported())
        val manager = ToolWindowManager.getInstance(project)
        val toolWindow = manager.registerToolWindow("WebGPT", true, ToolWindowAnchor.RIGHT)
        val factory = WebGPTToolWindowFactory()

        // When
        factory.createToolWindowContent(project, toolWindow)

        // Then
        assertEquals(1, toolWindow.contentManager.contentCount)
    }
}
