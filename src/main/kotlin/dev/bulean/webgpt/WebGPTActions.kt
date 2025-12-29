package dev.bulean.webgpt

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.ui.components.JBPanel
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.datatransfer.StringSelection
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel

class WebGPTActions(private val project: Project, private val browser: JBCefBrowser) {

    fun createButtonPanel(): JPanel {
        val buttonBox = Box.createHorizontalBox().apply {
            isOpaque = false
            add(JButton(PluginIcons.Reload).apply {
                toolTipText = "Reloads the embedded ChatGPT page"
                addActionListener { browser.loadURL(CHAT_OPENAI_URL) }
            })
            add(JButton(PluginIcons.Browser).apply {
                toolTipText = "Opens ChatGPT in your system browser"
                addActionListener { BrowserUtil.browse(CHAT_OPENAI_URL) }
            })
            add(JButton(PluginIcons.Explain).apply {
                toolTipText = "Explain the selected code"
                addActionListener {
                    val text = EditorUtils.getSelectedTextOrAll(project)
                    text?.let {
                        CopyPasteManager.getInstance().setContents(
                            StringSelection(buildExplainPrompt(it))
                        )
                        JBPopupFactory.getInstance().createMessage("Prompt with selected code copied to clipboard")
                            .showInFocusCenter()
                    }
                }
            })
            add(JButton(PluginIcons.Fix).apply {
                toolTipText = "Fix and optimize the selected code"
                addActionListener {
                    val code = EditorUtils.getSelectedTextOrAll(project)
                    val prompt = buildFixPrompt(code)
                    CopyPasteManager.getInstance().setContents(StringSelection(prompt))
                    JBPopupFactory.getInstance().createMessage("Prompt with selected code copied to clipboard")
                        .showInFocusCenter()
                }
            })
        }

        return JBPanel<Nothing>().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            isOpaque = false
            add(buttonBox)
        }
    }

    private fun buildExplainPrompt(code: String?): String {
        return """
            |# Explain this code in detail.
            |## Instructions:
            |- Describe what the code does.
            |- Explain key functions and logic.
            |- Highlight important variables and data structures.
            |- Provide context if necessary.
            |## Code:
            |```
            |$code
            |```
            """.trimMargin()
    }

    private fun buildFixPrompt(code: String?): String {
        return """
            |# Fix and optimize this code, and suggest architecture improvements.
            |## Instructions:
            |- Correct errors if exists.
            |- Recommend best practices, refactors, and simplifications.
            |- Suggest architecture improvements when relevant.
            |- Provide concrete examples if necessary.
            |## Code:
            |```
            |$code
            |```
            """.trimMargin()
    }

    private companion object {
        private const val CHAT_OPENAI_URL = "https://chat.openai.com"
    }
}
