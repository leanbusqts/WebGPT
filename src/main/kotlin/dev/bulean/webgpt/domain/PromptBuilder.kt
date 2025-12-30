package dev.bulean.webgpt.domain

interface PromptBuilder {
    fun buildPrompt(type: PromptType, code: String?): String
}
