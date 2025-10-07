package com.github.davidseptimus.lualsintellijplugin

import com.intellij.lang.Language

object LuaLanguage : Language("Lua") {
    private fun readResolve(): Any = LuaLanguage
    override fun getDisplayName(): String = "Lua"
}