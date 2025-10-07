package com.github.davidseptimus.lualsintellijplugin.run

import com.github.davidseptimus.lualsintellijplugin.settings.LuaLspSettings
import com.intellij.execution.configurations.RunConfigurationOptions

class LuaRunConfigurationOptions : RunConfigurationOptions() {
    private val luaExecutablePath = string("").provideDelegate(this, "luaExecutablePath")
    private val scriptPath = string("").provideDelegate(this, "scriptPath")
    private val scriptParameters = string("").provideDelegate(this, "scriptParameters")
    private val workingDirectory = string("").provideDelegate(this, "workingDirectory")

    fun getLuaExecutablePath(): String {
        val path = luaExecutablePath.getValue(this) ?: ""
        // If empty, inherit from IDE settings
        return path.ifEmpty { LuaLspSettings.getInstance().luaExecutablePath }
    }

    fun getLuaExecutablePathRaw(): String = luaExecutablePath.getValue(this) ?: ""

    fun setLuaExecutablePath(value: String) = luaExecutablePath.setValue(this, value)

    fun getScriptPath(): String = scriptPath.getValue(this) ?: ""
    fun setScriptPath(value: String) = scriptPath.setValue(this, value)

    fun getScriptParameters(): String = scriptParameters.getValue(this) ?: ""
    fun setScriptParameters(value: String) = scriptParameters.setValue(this, value)

    fun getWorkingDirectory(): String = workingDirectory.getValue(this) ?: ""
    fun setWorkingDirectory(value: String) = workingDirectory.setValue(this, value)
}