package com.github.davidseptimus.lualsintellijplugin

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

class LuaIcons {
    companion object {
        val File: Icon = IconLoader.getIcon("/icons/luaFileIcon.svg", LuaIcons::class.java)
        val Lua: Icon = IconLoader.getIcon("/icons/luaIcon.svg", LuaIcons::class.java)
    }

}
