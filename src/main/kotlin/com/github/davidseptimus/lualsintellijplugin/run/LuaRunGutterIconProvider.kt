package com.github.davidseptimus.lualsintellijplugin.run

import com.github.davidseptimus.lualsintellijplugin.LuaFileType
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.markup.HighlighterLayer
import com.intellij.openapi.editor.markup.HighlighterTargetArea
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class LuaRunGutterIconProvider : FileEditorManagerListener {

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        if (file.fileType != LuaFileType) {
            return
        }

        val project = source.project
        val editors = source.getAllEditors(file)

        for (fileEditor in editors) {
            if (fileEditor is TextEditor) {
                val editor = fileEditor.editor
                addGutterIcon(project, editor, file)
            }
        }
    }

    private fun addGutterIcon(project: Project, editor: Editor, file: VirtualFile) {
        val markupModel = editor.markupModel
        val document = editor.document

        // Add icon at the start of the file (line 0)
        if (document.textLength > 0) {
            val rangeHighlighter = markupModel.addRangeHighlighter(
                0,
                0,
                HighlighterLayer.LAST + 1,
                null,
                HighlighterTargetArea.LINES_IN_RANGE
            )

            rangeHighlighter.gutterIconRenderer = LuaRunGutterIconRenderer(project, file)
        }
    }
}