package dev.bulean.webgpt.domain

class CopyPromptUseCase(
    private val codeProvider: CodeProvider,
    private val promptBuilder: PromptBuilder,
    private val clipboardGateway: ClipboardGateway,
    private val messageGateway: MessageGateway
) {
    fun execute(type: PromptType) {
        val code = codeProvider.getSelectedOrAllText()
        if (code.isNullOrBlank()) {
            messageGateway.showInfo("No code selected")
            return
        }
        val prompt = promptBuilder.buildPrompt(type, code)
        clipboardGateway.copyText(prompt)
        messageGateway.showInfo("Prompt with selected code copied to clipboard")
    }
}
