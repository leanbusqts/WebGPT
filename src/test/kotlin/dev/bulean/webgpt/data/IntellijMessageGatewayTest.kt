package dev.bulean.webgpt.data

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import java.awt.KeyboardFocusManager
import java.awt.GraphicsEnvironment

class IntellijMessageGatewayTest : BasePlatformTestCase() {
    fun testShowInfoDoesNotThrow() {
        // Given
        if (GraphicsEnvironment.isHeadless()) {
            return
        }
        if (KeyboardFocusManager.getCurrentKeyboardFocusManager().activeWindow == null) {
            return
        }
        val gateway = IntellijMessageGateway()

        // When
        gateway.showInfo("test-message")

        // Then
        assertTrue(true)
    }
}
