package dev.bulean.webgpt.presentation

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import dev.bulean.webgpt.core.WebGPTConstants
import org.junit.Assume.assumeTrue
import java.awt.Component
import java.awt.Container
import java.awt.GraphicsEnvironment
import javax.swing.JButton

class WebGPTActionsTest : BasePlatformTestCase() {
    fun testCreatesExpectedButtons() {
        // Given
        assumeTrue(!GraphicsEnvironment.isHeadless())
        assumeTrue(JBCefApp.isSupported())
        val browser = JBCefBrowser(WebGPTConstants.CHAT_OPENAI_URL)
        val actions = WebGPTActions(project, browser)
        val panel = actions.createButtonPanel()

        // When
        val reload = findButtonByToolTip(panel, "Reloads the embedded ChatGPT page")
        val browserButton = findButtonByToolTip(panel, "Opens ChatGPT in your system browser")
        val explain = findButtonByToolTip(panel, "Explain the selected code")
        val fix = findButtonByToolTip(panel, "Fix and optimize the selected code")

        // Then
        assertNotNull(reload)
        assertNotNull(browserButton)
        assertNotNull(explain)
        assertNotNull(fix)
    }

    private fun findButtonByToolTip(container: Container, toolTip: String): JButton {
        val found = findComponent(container) { component ->
            component is JButton && component.toolTipText == toolTip
        }
        return found as? JButton ?: error("Button not found")
    }

    private fun findComponent(container: Container, predicate: (Component) -> Boolean): Component? {
        for (component in container.components) {
            if (predicate(component)) {
                return component
            }
            if (component is Container) {
                val nested = findComponent(component, predicate)
                if (nested != null) {
                    return nested
                }
            }
        }
        return null
    }
}
