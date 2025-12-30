package dev.bulean.webgpt.presentation

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBPanel
import com.intellij.ui.jcef.JBCefBrowser
import dev.bulean.webgpt.core.WebGPTConstants
import dev.bulean.webgpt.data.IntellijClipboardGateway
import dev.bulean.webgpt.data.IntellijCodeProvider
import dev.bulean.webgpt.data.IntellijMessageGateway
import dev.bulean.webgpt.domain.CopyPromptUseCase
import dev.bulean.webgpt.domain.DefaultPromptBuilder
import dev.bulean.webgpt.domain.PromptType
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel

class WebGPTActions(private val project: Project, private val browser: JBCefBrowser) {

    private val copyPromptUseCase = CopyPromptUseCase(
        IntellijCodeProvider(project),
        DefaultPromptBuilder(),
        IntellijClipboardGateway(),
        IntellijMessageGateway()
    )

    fun createButtonPanel(): JPanel {
        val buttonBox = Box.createHorizontalBox().apply {
            isOpaque = false
            add(JButton(PluginIcons.Reload).apply {
                toolTipText = "Reloads the embedded ChatGPT page"
                addActionListener { browser.loadURL(WebGPTConstants.CHAT_OPENAI_URL) }
            })
            add(JButton(PluginIcons.Browser).apply {
                toolTipText = "Opens ChatGPT in your system browser"
                addActionListener { BrowserUtil.browse(WebGPTConstants.CHAT_OPENAI_URL) }
            })
            add(JButton(PluginIcons.Explain).apply {
                toolTipText = "Explain the selected code"
                addActionListener { copyPromptUseCase.execute(PromptType.EXPLAIN) }
            })
            add(JButton(PluginIcons.Fix).apply {
                toolTipText = "Fix and optimize the selected code"
                addActionListener { copyPromptUseCase.execute(PromptType.FIX) }
            })
        }

        return JBPanel<Nothing>().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            isOpaque = false
            add(buttonBox)
        }
    }

}
