package com.github.davidseptimus.lualsintellijplugin.lsp

import com.github.davidseptimus.lualsintellijplugin.LuaFileType
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class LuaColorSettingsPage : ColorSettingsPage {
    override fun getDisplayName(): String = "Lua"
    override fun getIcon(): Icon = LuaFileType.icon

    override fun getHighlighter(): SyntaxHighlighter =
        SyntaxHighlighterFactory.getSyntaxHighlighter(LuaFileType, null, null)!!

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = arrayOf(
        // LSP Token Types
        AttributesDescriptor("Namespace", LuaTextAttributesKeys.NAMESPACE),
        AttributesDescriptor("Type", LuaTextAttributesKeys.TYPE),
        AttributesDescriptor("Class", LuaTextAttributesKeys.CLASS),
        AttributesDescriptor("Enum", LuaTextAttributesKeys.ENUM),
        AttributesDescriptor("Interface", LuaTextAttributesKeys.INTERFACE),
        AttributesDescriptor("Struct", LuaTextAttributesKeys.STRUCT),
        AttributesDescriptor("Type parameter", LuaTextAttributesKeys.TYPE_PARAMETER),
        AttributesDescriptor("Parameter", LuaTextAttributesKeys.PARAMETER),
        AttributesDescriptor("Variable", LuaTextAttributesKeys.VARIABLE),
        AttributesDescriptor("Property", LuaTextAttributesKeys.PROPERTY),
        AttributesDescriptor("Enum member", LuaTextAttributesKeys.ENUM_MEMBER),
        AttributesDescriptor("Event", LuaTextAttributesKeys.EVENT),
        AttributesDescriptor("Function", LuaTextAttributesKeys.FUNCTION),
        AttributesDescriptor("Method", LuaTextAttributesKeys.METHOD),
        AttributesDescriptor("Macro", LuaTextAttributesKeys.MACRO),
        AttributesDescriptor("Keyword", LuaTextAttributesKeys.KEYWORD),
        AttributesDescriptor("Modifier", LuaTextAttributesKeys.MODIFIER),
        AttributesDescriptor("Comment", LuaTextAttributesKeys.COMMENT),
        AttributesDescriptor("String", LuaTextAttributesKeys.STRING),
        AttributesDescriptor("Number", LuaTextAttributesKeys.NUMBER),
        AttributesDescriptor("Regexp", LuaTextAttributesKeys.REGEXP),
        AttributesDescriptor("Operator", LuaTextAttributesKeys.OPERATOR),
        AttributesDescriptor("Decorator", LuaTextAttributesKeys.DECORATOR),
        // LSP Modifiers
        AttributesDescriptor("Declaration", LuaTextAttributesKeys.DECLARATION),
        AttributesDescriptor("Definition", LuaTextAttributesKeys.DEFINITION),
        AttributesDescriptor("Readonly", LuaTextAttributesKeys.READONLY),
        AttributesDescriptor("Static", LuaTextAttributesKeys.STATIC),
        AttributesDescriptor("Deprecated", LuaTextAttributesKeys.DEPRECATED),
        AttributesDescriptor("Abstract", LuaTextAttributesKeys.ABSTRACT),
        AttributesDescriptor("Async", LuaTextAttributesKeys.ASYNC),
        AttributesDescriptor("Modification", LuaTextAttributesKeys.MODIFICATION),
        AttributesDescriptor("Documentation", LuaTextAttributesKeys.DOCUMENTATION),
        AttributesDescriptor("Default library", LuaTextAttributesKeys.DEFAULT_LIBRARY),
        AttributesDescriptor("Global", LuaTextAttributesKeys.GLOBAL),
    )

    override fun getColorDescriptors() = emptyArray<com.intellij.openapi.options.colors.ColorDescriptor>()

    override fun getDemoText(): String = """
-- Line comment
local function foo(x, y)
    local z = x + y -- Inline comment
    return z
end

local className = "MyClass"
local hexValue = 0xFF
local floatValue = 3.14
local str = 'single quoted string'
local str2 = "double quoted string"
local multi = [[multiline string]]
local escape = "\n\u{1F600}"
if z > 0 then
    goto label
end
::label::
"""

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null
}
