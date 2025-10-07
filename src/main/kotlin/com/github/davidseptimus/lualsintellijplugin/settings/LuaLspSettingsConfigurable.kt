package com.github.davidseptimus.lualsintellijplugin.settings

import com.github.davidseptimus.lualsintellijplugin.lsp.LuaLspServerManager
import com.intellij.icons.AllIcons
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.AnimatedIcon
import com.intellij.ui.SimpleListCellRenderer
import com.intellij.ui.components.JBLabel
import com.intellij.ui.dsl.builder.*
import javax.swing.SwingConstants
import javax.swing.SwingUtilities

class LuaLspSettingsConfigurable : BoundConfigurable("Lua Language Server") {
    private val settings = LuaLspSettings.getInstance()
    private var previousExecutablePath = settings.luaLanguageServerPath
    private var previousLuaExecutablePath = settings.luaExecutablePath
    private var previousLocale = settings.locale

    private data class ValidationResult(
        val isValid: Boolean,
        val version: String? = null,
        val errorMessage: String? = null
    )

    private fun validateLuaExecutable(path: String): ValidationResult {
        if (path.isBlank()) return ValidationResult(false)

        return try {
            val process = ProcessBuilder(path, "-v")
                .redirectErrorStream(true)
                .start()

            val output = process.inputStream.bufferedReader().use { it.readText() }
            val exitCode = process.waitFor()

            if (exitCode == 0 && output.contains("Lua")) {
                // Extract version from output like "Lua 5.4.8  Copyright (C) 1994-2025 Lua.org, PUC-Rio"
                val versionRegex = """Lua\s+([\d.]+)""".toRegex()
                val version = versionRegex.find(output)?.groupValues?.get(1)
                ValidationResult(true, version)
            } else {
                ValidationResult(false, errorMessage = "Not a valid Lua executable")
            }
        } catch (e: Exception) {
            ValidationResult(false, errorMessage = e.message ?: "Cannot execute")
        }
    }

    private fun validateLspExecutable(path: String): ValidationResult {
        if (path.isBlank()) return ValidationResult(false)

        return try {
            val process = ProcessBuilder(path, "--version")
                .redirectErrorStream(true)
                .start()

            val output = process.inputStream.bufferedReader().use { it.readText() }
            val exitCode = process.waitFor()

            if (exitCode == 0) {
                // Extract version from output - lua-language-server typically outputs just the version number
                val version = output.trim().lines().firstOrNull()?.trim()
                ValidationResult(true, version)
            } else {
                ValidationResult(false, errorMessage = "Not a valid lua-language-server executable")
            }
        } catch (e: Exception) {
            ValidationResult(false, errorMessage = e.message ?: "Cannot execute")
        }
    }

    override fun createPanel(): DialogPanel {
        lateinit var lspValidationIconLabel: JBLabel
        lateinit var lspErrorLabel: javax.swing.JLabel
        lateinit var lspErrorRow: Row
        lateinit var lspFieldComponent: com.intellij.openapi.ui.TextFieldWithBrowseButton

        lateinit var luaValidationIconLabel: JBLabel
        lateinit var luaErrorLabel: javax.swing.JLabel
        lateinit var luaErrorRow: Row
        lateinit var luaFieldComponent: com.intellij.openapi.ui.TextFieldWithBrowseButton

        // Set up LSP validation
        fun updateLspValidation() {
            val path = lspFieldComponent.text
            if (path.isBlank()) {
                lspValidationIconLabel.icon = null
                lspValidationIconLabel.text = ""
                lspErrorRow.visible(false)
                return
            }

            // Show spinner while validating
            lspValidationIconLabel.icon = AnimatedIcon.Default()
            lspValidationIconLabel.text = ""
            lspErrorRow.visible(false)

            // Run validation in background thread
            ApplicationManager.getApplication().executeOnPooledThread {
                val result = validateLspExecutable(path)

                // Update UI on EDT
                SwingUtilities.invokeLater {
                    when {
                        result.isValid -> {
                            lspValidationIconLabel.icon = AllIcons.General.InspectionsOK
                            lspValidationIconLabel.text = result.version?.let { "v$it" } ?: "Valid"
                            lspErrorRow.visible(false)
                        }
                        else -> {
                            lspValidationIconLabel.icon = AllIcons.General.Error
                            lspValidationIconLabel.text = ""
                            lspErrorLabel.text = result.errorMessage ?: "Invalid executable"
                            lspErrorRow.visible(true)
                        }
                    }
                }
            }
        }

        // Set up Lua validation
        fun updateLuaValidation() {
            val path = luaFieldComponent.text
            if (path.isBlank()) {
                luaValidationIconLabel.icon = null
                luaValidationIconLabel.text = ""
                luaErrorRow.visible(false)
                return
            }

            // Show spinner while validating
            luaValidationIconLabel.icon = AnimatedIcon.Default()
            luaValidationIconLabel.text = ""
            luaErrorRow.visible(false)

            // Run validation in background thread
            ApplicationManager.getApplication().executeOnPooledThread {
                val result = validateLuaExecutable(path)

                // Update UI on EDT
                SwingUtilities.invokeLater {
                    when {
                        result.isValid -> {
                            luaValidationIconLabel.icon = AllIcons.General.InspectionsOK
                            luaValidationIconLabel.text = result.version?.let { "v$it" } ?: "Valid"
                            luaErrorRow.visible(false)
                        }
                        else -> {
                            luaValidationIconLabel.icon = AllIcons.General.Error
                            luaValidationIconLabel.text = ""
                            luaErrorLabel.text = result.errorMessage ?: "Invalid executable"
                            luaErrorRow.visible(true)
                        }
                    }
                }
            }
        }

        return panel {
            row("Lua Language Server executable:") {
                val lspField = textFieldWithBrowseButton(
                    FileChooserDescriptor(true, false, false, false, false, false)
                        .withTitle("Select Lua Language Server Executable"),
                    null,
                    null
                ).bindText(settings::luaLanguageServerPath)

                lspFieldComponent = lspField.component

                lspValidationIconLabel = JBLabel().apply {
                    horizontalAlignment = SwingConstants.LEFT
                }

                cell(lspValidationIconLabel)
            }.rowComment("Path to lua-language-server executable (default: lua-language-server from PATH)")

            lspErrorRow = row("") {
                lspErrorLabel = label("").component.apply {
                    foreground = com.intellij.util.ui.JBUI.CurrentTheme.Label.errorForeground()
                }
            }.visible(false)

            row("Lua executable:") {
                val luaField = textFieldWithBrowseButton(
                    FileChooserDescriptor(true, false, false, false, false, false)
                        .withTitle("Select Lua Executable"),
                    null,
                    null
                ).bindText(settings::luaExecutablePath)

                luaFieldComponent = luaField.component

                luaValidationIconLabel = JBLabel().apply {
                    horizontalAlignment = SwingConstants.LEFT
                }

                cell(luaValidationIconLabel)
            }.rowComment("Path to Lua interpreter used for run configurations (default: lua from PATH)")

            luaErrorRow = row("") {
                luaErrorLabel = label("").component.apply {
                    foreground = com.intellij.util.ui.JBUI.CurrentTheme.Label.errorForeground()
                }
            }.visible(false)

            row("Language:") {
                val localeOptions = listOf<String?>(null) + LuaLocale.SUPPORTED_LOCALES
                comboBox(localeOptions, SimpleListCellRenderer.create("Use IDE language") {
                    it?.let { LuaLocale.getDisplayName(it) } ?: "Use IDE language"
                })
                    .bindItem({ settings.locale.ifEmpty { null } }, { settings.locale = it ?: "" })
            }.rowComment("Override the language for LSP messages. Defaults to IDE language with fallback to English.")

            onApply {
                // Trigger validation on apply
                updateLspValidation()
                updateLuaValidation()
            }
        }.also {
            // Validate on focus lost - LSP
            lspFieldComponent.textField.addFocusListener(object : java.awt.event.FocusAdapter() {
                override fun focusLost(e: java.awt.event.FocusEvent?) {
                    updateLspValidation()
                }
            })

            // Validate on file selection - LSP
            lspFieldComponent.addActionListener {
                updateLspValidation()
            }

            // Validate on focus lost - Lua
            luaFieldComponent.textField.addFocusListener(object : java.awt.event.FocusAdapter() {
                override fun focusLost(e: java.awt.event.FocusEvent?) {
                    updateLuaValidation()
                }
            })

            // Validate on file selection - Lua
            luaFieldComponent.addActionListener {
                updateLuaValidation()
            }

            // Initial validation
            updateLspValidation()
            updateLuaValidation()
        }
    }

    override fun apply() {
        super.apply()

        // Check if LSP-related settings changed
        val lspSettingsChanged = previousExecutablePath != settings.luaLanguageServerPath ||
            previousLocale != settings.locale

        // Update previous values
        previousExecutablePath = settings.luaLanguageServerPath
        previousLuaExecutablePath = settings.luaExecutablePath
        previousLocale = settings.locale

        // Restart LSP server only if LSP settings changed
        if (lspSettingsChanged) {
            ProjectManager.getInstance().openProjects.forEach { project ->
                LuaLspServerManager.restartServer(project)
            }
        }
    }
}