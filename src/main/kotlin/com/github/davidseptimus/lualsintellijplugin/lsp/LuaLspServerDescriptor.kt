package com.github.davidseptimus.lualsintellijplugin.lsp

import com.github.davidseptimus.lualsintellijplugin.LuaFileType
import com.github.davidseptimus.lualsintellijplugin.settings.LuaLocale
import com.github.davidseptimus.lualsintellijplugin.settings.LuaLspSettings
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import java.io.File

class LuaLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Lua") {


    override fun isSupportedFile(file: VirtualFile): Boolean {
        return file.fileType == LuaFileType
    }

    override fun createCommandLine(): GeneralCommandLine {
        val settings = LuaLspSettings.getInstance()
        return GeneralCommandLine(settings.luaLanguageServerPath).apply {
            // Set locale
            val locale = LuaLocale.getLocale(settings.locale)
            addParameter("--locale=$locale")

            // Set working directory to project root
            project.basePath?.let { basePath ->
                workDirectory = File(basePath)

                // Prefer .luarc over .luarc.json if it exists
                val luarcFile = File(basePath, ".luarc")
                if (luarcFile.exists()) {
                    addParameter("--configpath=${luarcFile.absolutePath}")
                }
            }
        }
    }
}

class LuaLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
    ) {
        if (file.fileType == LuaFileType) {
            serverStarter.ensureServerStarted(LuaLspServerDescriptor(project))
        }
    }
}