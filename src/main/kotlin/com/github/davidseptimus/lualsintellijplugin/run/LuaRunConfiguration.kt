package com.github.davidseptimus.lualsintellijplugin.run

import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.RunConfigurationBase
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project

class LuaRunConfiguration(
    project: Project,
    factory: ConfigurationFactory,
    name: String
) : RunConfigurationBase<LuaRunConfigurationOptions>(project, factory, name) {

    override fun getOptionsClass(): Class<LuaRunConfigurationOptions> {
        return LuaRunConfigurationOptions::class.java
    }

    public override fun getOptions(): LuaRunConfigurationOptions {
        return super.getOptions() as LuaRunConfigurationOptions
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> {
        return LuaRunConfigurationEditor(project)
    }

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState {
        return LuaCommandLineState(this, environment)
    }
}