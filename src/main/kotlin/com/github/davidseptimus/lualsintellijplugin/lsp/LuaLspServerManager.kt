package com.github.davidseptimus.lualsintellijplugin.lsp

import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServerManager

object LuaLspServerManager {
    /**
     * Restarts the Lua language server for the given project.
     * This will stop the current server instance and start a new one.
     */
    fun restartServer(project: Project) {
        val lspServerManager = LspServerManager.getInstance(project)
        lspServerManager.stopAndRestartIfNeeded(LuaLspServerSupportProvider::class.java)
    }
}