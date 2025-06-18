package dev.bulean.webgpt

import com.intellij.ui.components.JBPanel
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.JScrollPane

class WebGPTPanel {

    private val browser = JBCefBrowser(CHAT_OPENAI_URL)

    fun build(): JPanel {
        val rootPanel = JBPanel<Nothing>().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
        }

        val buttonPanel = WebGPTActions(browser).createButtonPanel()

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

    private companion object {
        private const val CHAT_OPENAI_URL = "https://chat.openai.com"
    }
}

