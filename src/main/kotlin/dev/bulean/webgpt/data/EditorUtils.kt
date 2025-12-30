package dev.bulean.webgpt.data

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project

object EditorUtils {
    fun getSelectedTextOrAll(project: Project): String? {
        val editor = FileEditorManager.getInstance(project).selectedTextEditor ?: return null
        val selection = editor.selectionModel.selectedText
        return selection ?: editor.document.text
    }
}
