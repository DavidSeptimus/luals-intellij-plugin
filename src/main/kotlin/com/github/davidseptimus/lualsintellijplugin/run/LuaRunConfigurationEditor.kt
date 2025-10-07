package com.github.davidseptimus.lualsintellijplugin.run

import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

class LuaRunConfigurationEditor(private val project: Project) : SettingsEditor<LuaRunConfiguration>() {
    private val luaExecutableField = TextFieldWithBrowseButton()
    private val scriptPathField = TextFieldWithBrowseButton()
    private val scriptParametersField = JBTextField()
    private val workingDirectoryField = TextFieldWithBrowseButton()

    init {
        // Configure Lua executable browser
        luaExecutableField.addBrowseFolderListener(
            "Select Lua Executable",
            "Choose the Lua interpreter executable",
            project,
            FileChooserDescriptor(true, false, false, false, false, false)
        )

        // Configure script path browser
        scriptPathField.addBrowseFolderListener(
            "Select Lua Script",
            "Choose the Lua script to run",
            project,
            FileChooserDescriptor(true, false, false, false, false, false)
                .withFileFilter { it.extension == "lua" }
        )

        // Configure working directory browser
        workingDirectoryField.addBrowseFolderListener(
            "Select Working Directory",
            "Choose the working directory for the script",
            project,
            FileChooserDescriptor(false, true, false, false, false, false)
        )
    }

    override fun createEditor(): JComponent = panel {
        row("Lua executable:") {
            cell(luaExecutableField)
                .comment("Path to the Lua interpreter (default: lua from PATH)")
        }
        row("Script path:") {
            cell(scriptPathField)
                .comment("Path to the Lua script to execute")
        }
        row("Script parameters:") {
            cell(scriptParametersField)
                .comment("Command-line arguments passed to the script")
        }
        row("Working directory:") {
            cell(workingDirectoryField)
                .comment("Working directory for script execution (default: script directory)")
        }
    }

    override fun resetEditorFrom(config: LuaRunConfiguration) {
        luaExecutableField.text = config.options.getLuaExecutablePath()
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