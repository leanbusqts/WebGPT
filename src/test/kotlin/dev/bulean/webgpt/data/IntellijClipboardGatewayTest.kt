package dev.bulean.webgpt.data

import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import java.awt.datatransfer.DataFlavor

class IntellijClipboardGatewayTest : BasePlatformTestCase() {
    fun testCopyTextWritesToClipboard() {
        // Given
        val gateway = IntellijClipboardGateway()
        val expected = "clipboard-value"

        // When
        gateway.copyText(expected)

        // Then
        val contents = CopyPasteManager.getInstance().contents
        val actual = contents?.getTransferData(DataFlavor.stringFlavor) as? String
        assertEquals(expected, actual)
    }
}
