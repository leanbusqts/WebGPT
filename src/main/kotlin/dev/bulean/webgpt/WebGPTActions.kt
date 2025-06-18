package dev.bulean.webgpt

import com.intellij.ide.BrowserUtil
import com.intellij.ui.components.JBPanel
import com.intellij.ui.jcef.JBCefBrowser
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel

class WebGPTActions(private val browser: JBCefBrowser) {

    fun createButtonPanel(): JPanel {
        val buttonBox = Box.createHorizontalBox().apply {
            isOpaque = false
            add(JButton(RELOAD_CHATGPT_TEXT).apply {
                addActionListener { browser.loadURL(CHAT_OPENAI_URL) }
                toolTipText = RELOAD_CHATGPT_TOOLTIP
            })
            add(Box.createHorizontalStrut(8))
            add(JButton(OPEN_IN_BROWSER_TEXT).apply {
                addActionListener { BrowserUtil.browse(CHAT_OPENAI_URL) }
                toolTipText = OPEN_IN_BROWSER_TOOLTIP
            })
        }

        return JBPanel<Nothing>().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            isOpaque = false
            add(buttonBox)
        }
    }

    private companion object {
        private const val CHAT_OPENAI_URL = "https://chat.openai.com"
        private const val RELOAD_CHATGPT_TEXT = "Reload ChatGPT"
        private const val RELOAD_CHATGPT_TOOLTIP = "Reloads the embedded ChatGPT page"
        private const val OPEN_IN_BROWSER_TEXT = "Open in browser"
        private const val OPEN_IN_BROWSER_TOOLTIP = "Opens ChatGPT in your system browser"
    }
}
