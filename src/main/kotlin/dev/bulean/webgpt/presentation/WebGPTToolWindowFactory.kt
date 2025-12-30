package dev.bulean.webgpt.presentation

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class WebGPTToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentPanel = WebGPTPanel(project).build()
        val content = ContentFactory.getInstance().createContent(contentPanel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
