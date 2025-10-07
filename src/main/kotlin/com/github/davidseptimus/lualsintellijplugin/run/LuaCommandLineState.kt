package com.github.davidseptimus.lualsintellijplugin.run

import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.KillableColoredProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import java.io.File

class LuaCommandLineState(
    private val config: LuaRunConfiguration,
    environment: ExecutionEnvironment
) : CommandLineState(environment) {

    override fun startProcess(): ProcessHandler {
        val commandLine = createCommandLine()
        val processHandler = KillableColoredProcessHandler(commandLine)
        ProcessTerminatedListener.attach(processHandler)
        return processHandler
    }

    private fun createCommandLine(): GeneralCommandLine {
        val options = config.options
        val luaExecutable = options.getLuaExecutablePath().ifEmpty { "lua" }
        val scriptPath = options.getScriptPath()

        if (scriptPath.isEmpty()) {
            throw RuntimeException("Script path is not specified")
        }

        val scriptFile = File(scriptPath)
        if (!scriptFile.exists()) {
            throw RuntimeException("Script file does not exist: $scriptPath")
        }

        val commandLine = GeneralCommandLine(luaExecutable, scriptPath)

        // Add script parameters if provided
        val parameters = options.getScriptParameters()
        if (parameters.isNotEmpty()) {
            commandLine.addParameters(parameters.split("\\s+".toRegex()))
        }

        // Set working directory
        val workingDir = options.getWorkingDirectory()
        if (workingDir.isNotEmpty()) {
            commandLine.setWorkDirectory(workingDir)
        } else {
            // Default to script's directory
            commandLine.setWorkDirectory(scriptFile.parent)
        }

        return commandLine
    }
}