package com.github.davidseptimus.lualsintellijplugin.lsp

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors

object LuaTextAttributesKeys {
    // Type-related tokens
    val NAMESPACE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_NAMESPACE", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
    val TYPE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_TYPE", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
    val CLASS: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_CLASS", DefaultLanguageHighlighterColors.CLASS_NAME)
    val ENUM: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_ENUM", DefaultLanguageHighlighterColors.CLASS_NAME)
    val INTERFACE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_INTERFACE", DefaultLanguageHighlighterColors.INTERFACE_NAME)
    val STRUCT: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_STRUCT", DefaultLanguageHighlighterColors.CLASS_NAME)
    val TYPE_PARAMETER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_TYPE_PARAMETER", DefaultLanguageHighlighterColors.CLASS_REFERENCE)

    // Variable-related tokens
    val PARAMETER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER)
    val VARIABLE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
    val PROPERTY: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_PROPERTY", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val ENUM_MEMBER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_ENUM_MEMBER", DefaultLanguageHighlighterColors.STATIC_FIELD)
    val EVENT: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_EVENT", DefaultLanguageHighlighterColors.STATIC_FIELD)

    // Function-related tokens
    val FUNCTION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
    val METHOD: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_METHOD", DefaultLanguageHighlighterColors.INSTANCE_METHOD)
    val MACRO: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_MACRO", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)

    // Keyword and operators
    val KEYWORD: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
    val MODIFIER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_MODIFIER", DefaultLanguageHighlighterColors.KEYWORD)
    val OPERATOR: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)

    // Literals
    val COMMENT: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
    val STRING: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_STRING", DefaultLanguageHighlighterColors.STRING)
    val NUMBER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
    val REGEXP: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_REGEXP", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE)

    // Annotations and decorators
    val DECORATOR: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DECORATOR", DefaultLanguageHighlighterColors.METADATA)

    // Modifiers (used by lua-language-server for additional semantic information)
    val DECLARATION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DECLARATION", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
    val DEFINITION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DEFINITION", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
    val READONLY: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_READONLY", DefaultLanguageHighlighterColors.CONSTANT)
    val STATIC: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_STATIC", DefaultLanguageHighlighterColors.STATIC_FIELD)
    val DEPRECATED: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DEPRECATED", DefaultLanguageHighlighterColors.IDENTIFIER)
    val ABSTRACT: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_ABSTRACT", DefaultLanguageHighlighterColors.IDENTIFIER)
    val ASYNC: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_ASYNC", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
    val MODIFICATION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_MODIFICATION", DefaultLanguageHighlighterColors.REASSIGNED_LOCAL_VARIABLE)
    val DOCUMENTATION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DOCUMENTATION", DefaultLanguageHighlighterColors.DOC_COMMENT)
    val DEFAULT_LIBRARY: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_DEFAULT_LIBRARY", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
    val GLOBAL: TextAttributesKey = TextAttributesKey.createTextAttributesKey("LUA_GLOBAL", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE)

}