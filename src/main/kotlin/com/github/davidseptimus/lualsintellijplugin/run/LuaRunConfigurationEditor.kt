package com.github.davidseptimus.lualsintellijplugin.run

import com.github.davidseptimus.lualsintellijplugin.settings.LuaLspSettings
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

class LuaRunConfigurationEditor(project: Project) : SettingsEditor<LuaRunConfiguration>() {
    private val luaExecutableField = TextFieldWithBrowseButton()
    private val scriptPathField = TextFieldWithBrowseButton()
    private val scriptParametersField = JBTextField()
    private val workingDirectoryField = TextFieldWithBrowseButton()

    init {
        // Configure Lua executable browser
        luaExecutableField.addBrowseFolderListener(
            project,
            FileChooserDescriptor(true, false, false, false, false, false)
                .withTitle("Select Lua Executable")
                .withDescription("Choose the Lua interpreter executable")
        )

        // Configure script path browser
        scriptPathField.addBrowseFolderListener(
            project,
            FileChooserDescriptor(true, false, false, false, false, false)
                .withTitle("Select Lua Script")
                .withDescription("Choose the Lua script to run")
                .withFileFilter { it.extension == "lua" }
        )

        // Configure working directory browser
        workingDirectoryField.addBrowseFolderListener(
            project,
            FileChooserDescriptor(false, true, false, false, false, false)
                .withTitle("Select Working Directory")
                .withDescription("Choose the working directory for the script")
        )
    }

    override fun createEditor(): JComponent = panel {
        row("Lua executable:") {
            cell(luaExecutableField)
                .align(AlignX.FILL)
                .comment("Path to the Lua interpreter (leave empty to use IDE setting: ${LuaLspSettings.getInstance().luaExecutablePath})")
        }
        row("Script path:") {
            cell(scriptPathField)
                .align(AlignX.FILL)
                .comment("Path to the Lua script to execute")
        }
        row("Script parameters:") {
            cell(scriptParametersField)
                .align(AlignX.FILL)
                .comment("Command-line arguments passed to the script")
        }
        row("Working directory:") {
            cell(workingDirectoryField)
                .align(AlignX.FILL)
                .comment("Working directory for script execution (default: script directory)")
        }
    }

    override fun resetEditorFrom(config: LuaRunConfiguration) {
        // Get the stored value (may be empty to inherit from settings)
        luaExecutableField.text = config.options.getLuaExecutablePathRaw()
        scriptPathField.text = config.options.getScriptPath()
        scriptParametersField.text = config.options.getScriptParameters()
        workingDirectoryField.text = config.options.getWorkingDirectory()
    }

    override fun applyEditorTo(config: LuaRunConfiguration) {
        config.options.setLuaExecutablePath(luaExecutableField.text)
        config.options.setScriptPath(scriptPathField.text)
        config.options.setScriptParameters(scriptParametersField.text)
        config.options.setWorkingDirectory(workingDirectoryField.text)
    }
}