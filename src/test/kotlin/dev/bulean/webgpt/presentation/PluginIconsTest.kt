package dev.bulean.webgpt.presentation

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class PluginIconsTest : BasePlatformTestCase() {
    fun testIconsAreLoaded() {
        // Given
        val reload = PluginIcons.Reload
        val browser = PluginIcons.Browser
        val explain = PluginIcons.Explain
        val fix = PluginIcons.Fix

        // When
        val icons = listOf(reload, browser, explain, fix)

        // Then
        assertEquals(4, icons.size)
        assertTrue(icons.all { it != null })
    }
}
