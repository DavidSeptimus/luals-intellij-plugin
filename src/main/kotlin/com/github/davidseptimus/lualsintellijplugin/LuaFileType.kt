package com.github.davidseptimus.lualsintellijplugin

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object LuaFileType : LanguageFileType(LuaLanguage) {
    override fun getName(): String = "Lua"

    override fun getDescription(): String = "Lua source file"

    override fun getDefaultExtension(): String = "lua"

    override fun getIcon(): Icon = LuaIcons.FILE
}