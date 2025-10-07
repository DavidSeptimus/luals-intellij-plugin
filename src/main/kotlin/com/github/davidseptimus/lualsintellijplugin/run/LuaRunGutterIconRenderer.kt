package com.github.davidseptimus.lualsintellijplugin.run

import com.intellij.execution.ExecutionManager
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.execution.runners.ExecutionUtil
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

class LuaRunGutterIconRenderer(
    private val project: Project,
    private val file: VirtualFile
) : GutterIconRenderer() {

    override fun getIcon(): Icon = AllIcons.RunConfigurations.TestState.Run

    override fun getTooltipText(): String = "Run '${file.name}'"

    override fun isNavigateAction(): Boolean = true

    override fun getClickAction(): AnAction {
        return object : AnAction() {
            override fun actionPerformed(e: AnActionEvent) {
                val configurationType = LuaRunConfigurationType.getInstance()
                val factory = configurationType.configurationFactories[0]

                val runManager = com.intellij.execution.RunManager.getInstance(project)
                val settings = runManager.createConfiguration(file.nameWithoutExtension, factory)
                val configuration = settings.configuration as LuaRunConfiguration

                configuration.options.setScriptPath(file.path)
                configuration.options.setWorkingDirectory(file.parent.path)

                runManager.addConfiguration(settings)
                runManager.selectedConfiguration = settings

                ExecutionUtil.runConfiguration(settings, DefaultRunExecutor.getRunExecutorInstance())
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LuaRunGutterIconRenderer) return false
        return file == other.file
    }

    override fun hashCode(): Int = file.hashCode()
}