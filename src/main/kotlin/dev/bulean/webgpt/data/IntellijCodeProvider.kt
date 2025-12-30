package dev.bulean.webgpt.data

import com.intellij.openapi.project.Project
import dev.bulean.webgpt.domain.CodeProvider

class IntellijCodeProvider(private val project: Project) : CodeProvider {
    override fun getSelectedOrAllText(): String? {
        return EditorUtils.getSelectedTextOrAll(project)
    }
}
