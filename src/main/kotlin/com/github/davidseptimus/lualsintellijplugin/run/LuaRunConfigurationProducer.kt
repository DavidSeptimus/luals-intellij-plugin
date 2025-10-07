package com.github.davidseptimus.lualsintellijplugin.run

import com.github.davidseptimus.lualsintellijplugin.LuaFileType
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement

class LuaRunConfigurationProducer : LazyRunConfigurationProducer<LuaRunConfiguration>() {

    override fun getConfigurationFactory(): ConfigurationFactory {
        return LuaRunConfigurationType.getInstance().configurationFactories[0]
    }

    override fun isConfigurationFromContext(
        configuration: LuaRunConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val location = context.location ?: return false
        val file = location.virtualFile ?: return false

        if (file.fileType != LuaFileType) {
            return false
        }

        val options = configuration.options
        return options.getScriptPath() == file.path
    }

    override fun setupConfigurationFromContext(
        configuration: LuaRunConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val location = context.location ?: return false
        val file = location.virtualFile ?: return false

        if (file.fileType != LuaFileType) {
            return false
        }

        val options = configuration.options
        options.setScriptPath(file.path)
        options.setWorkingDirectory(file.parent.path)

        configuration.name = file.nameWithoutExtension

        return true
    }
}