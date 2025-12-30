package dev.bulean.webgpt.domain

class DefaultPromptBuilder : PromptBuilder {
    override fun buildPrompt(type: PromptType, code: String?): String {
        return when (type) {
            PromptType.EXPLAIN -> buildExplainPrompt(code)
            PromptType.FIX -> buildFixPrompt(code)
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
}
