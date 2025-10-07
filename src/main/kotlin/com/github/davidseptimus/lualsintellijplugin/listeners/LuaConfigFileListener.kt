package com.github.davidseptimus.lualsintellijplugin.listeners

import com.github.davidseptimus.lualsintellijplugin.lsp.LuaLspServerManager
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileDocumentManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectLocator
import com.intellij.openapi.vfs.VirtualFile

class LuaConfigFileListener : FileDocumentManagerListener {
    override fun beforeDocumentSaving(document: com.intellij.openapi.editor.Document) {
        val file = FileDocumentManager.getInstance().getFile(document) ?: return

        // Check if this is a .luarc or .luarc.json file
        if (!isLuaConfigFile(file)) {
            return
        }

        // Find the project this file belongs to
        val project = ProjectLocator.getInstance().guessProjectForFile(file) ?: return

        // Check if file is in project root
        if (file.parent?.path != project.basePath) {
            return
        }

        // Show notification with restart action
        showRestartNotification(project, file.name)
    }

    private fun isLuaConfigFile(file: VirtualFile): Boolean {
        val name = file.name
        return name == ".luarc" || name == ".luarc.json"
    }

    private fun showRestartNotification(project: Project, fileName: String) {
        val notification = NotificationGroupManager.getInstance()
            .getNotificationGroup("Lua Language Server")
            .createNotification(
                "Lua Configuration Changed",
                "The file '$fileName' was modified. Restart the language server to apply changes.",
                NotificationType.INFORMATION
            )

        notification.addAction(object : NotificationAction("Restart Language Server") {
            override fun actionPerformed(e: AnActionEvent, notification: Notification) {
                LuaLspServerManager.restartServer(project)
                notification.expire()
            }
        })

        notification.addAction(object : NotificationAction("Dismiss") {
            override fun actionPerformed(e: AnActionEvent, notification: Notification) {
                notification.expire()
            }
        })

        notification.notify(project)
    }
}