package dev.bulean.webgpt.presentation

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.ui.jcef.JBCefApp
import org.junit.Assume.assumeTrue
import java.awt.GraphicsEnvironment
import javax.swing.JScrollPane

class WebGPTPanelTest : BasePlatformTestCase() {
    fun testBuildCreatesExpectedLayout() {
        // Given
        assumeTrue(!GraphicsEnvironment.isHeadless())
        assumeTrue(JBCefApp.isSupported())
        val panel = WebGPTPanel(project)

        // When
        val result = panel.build()

        // Then
        assertEquals(2, result.componentCount)
        assertTrue(result.getComponent(0) is JScrollPane)
    }
}
