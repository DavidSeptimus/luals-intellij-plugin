package com.github.davidseptimus.lualsintellijplugin.lsp

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors

object LuaTextAttributesKeys {
    val NAMESPACE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_NAMESPACE", DefaultLanguageHighlighterColors.IDENTIFIER)
    val TYPE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_TYPE", DefaultLanguageHighlighterColors.IDENTIFIER)
    val CLASS: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_CLASS", DefaultLanguageHighlighterColors.CLASS_NAME)
    val ENUM: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_ENUM", DefaultLanguageHighlighterColors.CLASS_NAME)
    val INTERFACE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_INTERFACE", DefaultLanguageHighlighterColors.INTERFACE_NAME)
    val STRUCT: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_STRUCT", DefaultLanguageHighlighterColors.IDENTIFIER)
    val TYPE_PARAMETER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_TYPE_PARAMETER", DefaultLanguageHighlighterColors.IDENTIFIER)
    val PARAMETER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER)
    val VARIABLE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
    val PROPERTY: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_PROPERTY", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val ENUM_MEMBER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_ENUM_MEMBER", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val EVENT: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_EVENT", DefaultLanguageHighlighterColors.IDENTIFIER)
    val FUNCTION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
    val METHOD: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_METHOD", DefaultLanguageHighlighterColors.INSTANCE_METHOD)
    val MACRO: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_MACRO", DefaultLanguageHighlighterColors.IDENTIFIER)
    val KEYWORD: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
    val MODIFIER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_MODIFIER", HighlighterColors.TEXT)
    val COMMENT: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
    val STRING: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_STRING", DefaultLanguageHighlighterColors.STRING)
    val NUMBER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
    val REGEXP: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_REGEXP", HighlighterColors.TEXT)
    val OPERATOR: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
    val DECORATOR: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DECORATOR", HighlighterColors.TEXT)

    // Modifiers
    val DECLARATION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DECLARATION", HighlighterColors.TEXT)
    val DEFINITION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DEFINITION", HighlighterColors.TEXT)
    val READONLY: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_READONLY", HighlighterColors.TEXT)
    val STATIC: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_STATIC", DefaultLanguageHighlighterColors.STATIC_FIELD)
    val DEPRECATED: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DEPRECATED")
    val ABSTRACT: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_ABSTRACT", HighlighterColors.TEXT)
    val ASYNC: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_ASYNC", HighlighterColors.TEXT)
    val MODIFICATION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_MODIFICATION", HighlighterColors.TEXT)
    val DOCUMENTATION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DOCUMENTATION", DefaultLanguageHighlighterColors.DOC_COMMENT)
    val DEFAULT_LIBRARY: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DEFAULT_LIBRARY", HighlighterColors.TEXT)
    val GLOBAL: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_GLOBAL", HighlighterColors.TEXT)

}