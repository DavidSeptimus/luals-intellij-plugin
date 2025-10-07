package com.github.davidseptimus.lualsintellijplugin.settings

import com.github.davidseptimus.lualsintellijplugin.lsp.LuaLspServerManager
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.SimpleListCellRenderer
import com.intellij.ui.dsl.builder.bindItem
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel

class LuaLspSettingsConfigurable : BoundConfigurable("Lua Language Server") {
    private val settings = LuaLspSettings.getInstance()
    private var previousExecutablePath = settings.luaLanguageServerPath
    private var previousLocale = settings.locale

    override fun createPanel(): DialogPanel = panel {
        row("Lua Language Server executable:") {
            textFieldWithBrowseButton(
                FileChooserDescriptor(true, false, false, false, false, false)
                    .withTitle("Select Lua Language Server Executable"),
                null,
                null
            )
                .bindText(settings::luaLanguageServerPath)
                .comment("Path to lua-language-server executable (default: lua-language-server from PATH)")
        }
        row("Language:") {
            val localeOptions = listOf("") + LuaLocale.SUPPORTED_LOCALES
            comboBox(localeOptions, SimpleListCellRenderer.create("") { LuaLocale.getDisplayName(it) })
                .bindItem({ settings.locale.ifEmpty { null } }, { settings.locale = it ?: "" })
        }.rowComment("Override the language for LSP messages. Defaults to IDE language with fallback to English.")
    }

    override fun apply() {
        super.apply()

        // Check if settings changed
        val settingsChanged = previousExecutablePath != settings.luaLanguageServerPath ||
            previousLocale != settings.locale

        if (settingsChanged) {
            // Update previous values
            previousExecutablePath = settings.luaLanguageServerPath
            previousLocale = settings.locale

            // Restart LSP server for all open projects
            ProjectManager.getInstance().openProjects.forEach { project ->
                LuaLspServerManager.restartServer(project)
            }
        }
    }
}