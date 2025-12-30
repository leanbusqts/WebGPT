package dev.bulean.webgpt.domain

interface ClipboardGateway {
    fun copyText(text: String)
}
