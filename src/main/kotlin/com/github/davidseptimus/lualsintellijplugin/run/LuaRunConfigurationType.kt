package com.github.davidseptimus.lualsintellijplugin.run

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.icons.AllIcons
import com.intellij.openapi.project.Project

class LuaRunConfigurationType : ConfigurationTypeBase(
    "LuaRunConfiguration",
    "Lua",
    "Lua run configuration",
    AllIcons.RunConfigurations.Application
) {
    init {
        addFactory(object : ConfigurationFactory(this) {
            override fun getId(): String = "Lua Configuration Factory"

            override fun createTemplateConfiguration(project: Project): RunConfiguration {
                return LuaRunConfiguration(project, this, "Lua")
            }
        })
    }

    companion object {
        fun getInstance(): LuaRunConfigurationType {
            return com.intellij.execution.configurations.ConfigurationTypeUtil.findConfigurationType(
                LuaRunConfigurationType::class.java
            )
        }
    }
}