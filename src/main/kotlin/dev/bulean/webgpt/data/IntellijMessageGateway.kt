package dev.bulean.webgpt.data

import com.intellij.openapi.ui.popup.JBPopupFactory
import dev.bulean.webgpt.domain.MessageGateway

class IntellijMessageGateway : MessageGateway {
    override fun showInfo(message: String) {
        JBPopupFactory.getInstance().createMessage(message).showInFocusCenter()
    }
}
