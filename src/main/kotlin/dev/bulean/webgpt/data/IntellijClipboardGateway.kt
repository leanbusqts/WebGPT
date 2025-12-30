package dev.bulean.webgpt.data

import com.intellij.openapi.ide.CopyPasteManager
import dev.bulean.webgpt.domain.ClipboardGateway
import java.awt.datatransfer.StringSelection

class IntellijClipboardGateway : ClipboardGateway {
    override fun copyText(text: String) {
        CopyPasteManager.getInstance().setContents(StringSelection(text))
    }
}
