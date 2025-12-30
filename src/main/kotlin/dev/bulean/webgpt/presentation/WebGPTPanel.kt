package dev.bulean.webgpt.presentation

import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.ui.components.JBPanel
import com.intellij.ui.jcef.JBCefBrowser
import dev.bulean.webgpt.core.WebGPTConstants
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.JScrollPane

class WebGPTPanel(private val project: Project) : Disposable {

    private val browser = JBCefBrowser(WebGPTConstants.CHAT_OPENAI_URL)

    fun build(): JPanel {
        Disposer.register(this, browser)
        val rootPanel = JBPanel<Nothing>().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
        }

        val buttonPanel = WebGPTActions(project, browser).createButtonPanel()

        val scrollPane = JScrollPane(buttonPanel).apply {
            horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
            verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_NEVER
            preferredSize = Dimension(Short.MAX_VALUE.toInt(), 50)
            minimumSize = Dimension(0, 50)
            maximumSize = Dimension(Int.MAX_VALUE, 50)
            border = null
            isOpaque = false
            viewport.isOpaque = false
            viewport.preferredSize = buttonPanel.preferredSize
        }

        val browserWrapper = JPanel(BorderLayout()).apply {
            add(browser.component, BorderLayout.CENTER)
        }

        rootPanel.add(scrollPane)
        rootPanel.add(browserWrapper)

        return rootPanel
    }

    override fun dispose() = Unit
}
