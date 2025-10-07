package com.github.davidseptimus.lualsintellijplugin.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "LuaLspSettings",
    storages = [Storage("LuaLspSettings.xml")]
)
class LuaLspSettings : PersistentStateComponent<LuaLspSettings> {
    var luaLanguageServerPath: String = "lua-language-server"
    var luaExecutablePath: String = "lua"
    var locale: String = "" // Empty string means "use IDE language"

    override fun getState(): LuaLspSettings = this

    override fun loadState(state: LuaLspSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        fun getInstance(): LuaLspSettings = service()
    }
}