package com.github.davidseptimus.lualsintellijplugin.lsp

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors

object LuaTextAttributesKeys {
    // ========== Type-related tokens ==========
    val NAMESPACE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_NAMESPACE",
        DefaultLanguageHighlighterColors.CLASS_REFERENCE
    )
    val TYPE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_TYPE",
        DefaultLanguageHighlighterColors.CLASS_REFERENCE
    )
    val CLASS: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_CLASS",
        DefaultLanguageHighlighterColors.CLASS_NAME
    )
    val ENUM: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_ENUM",
        DefaultLanguageHighlighterColors.CLASS_NAME
    )
    val INTERFACE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_INTERFACE",
        DefaultLanguageHighlighterColors.INTERFACE_NAME
    )
    val STRUCT: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_STRUCT",
        DefaultLanguageHighlighterColors.CLASS_NAME
    )
    val TYPE_PARAMETER: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_TYPE_PARAMETER",
        DefaultLanguageHighlighterColors.CLASS_REFERENCE
    )

    // ========== Variable-related tokens ==========
    // Base variable
    val VARIABLE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_VARIABLE",
        DefaultLanguageHighlighterColors.LOCAL_VARIABLE
    )

    // Modified variables
    val GLOBAL_VARIABLE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_GLOBAL_VARIABLE",
        DefaultLanguageHighlighterColors.GLOBAL_VARIABLE
    )
    val READONLY_VARIABLE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_READONLY_VARIABLE",
        DefaultLanguageHighlighterColors.CONSTANT
    )
    val DEFAULT_LIBRARY_VARIABLE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_DEFAULT_LIBRARY_VARIABLE",
        DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL
    )

    // Parameter
    val PARAMETER: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_PARAMETER",
        DefaultLanguageHighlighterColors.PARAMETER
    )

    // ========== Property-related tokens ==========
    val PROPERTY: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_PROPERTY",
        DefaultLanguageHighlighterColors.INSTANCE_FIELD
    )
    val READONLY_PROPERTY: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_READONLY_PROPERTY",
        DefaultLanguageHighlighterColors.CONSTANT
    )
    val STATIC_PROPERTY: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_STATIC_PROPERTY",
        DefaultLanguageHighlighterColors.STATIC_FIELD
    )

    // ========== Enum and Event tokens ==========
    val ENUM_MEMBER: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_ENUM_MEMBER",
        DefaultLanguageHighlighterColors.STATIC_FIELD
    )
    val EVENT: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_EVENT",
        DefaultLanguageHighlighterColors.STATIC_FIELD
    )

    // ========== Function-related tokens ==========
    // Base function
    val FUNCTION: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_FUNCTION",
        DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
    )

    // Modified functions
    val GLOBAL_FUNCTION: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_GLOBAL_FUNCTION",
        DefaultLanguageHighlighterColors.STATIC_METHOD
    )
    val DEFAULT_LIBRARY_FUNCTION: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_DEFAULT_LIBRARY_FUNCTION",
        DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL
    )

    // ========== Method-related tokens ==========
    val METHOD: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_METHOD",
        DefaultLanguageHighlighterColors.INSTANCE_METHOD
    )
    val STATIC_METHOD: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_STATIC_METHOD",
        DefaultLanguageHighlighterColors.STATIC_METHOD
    )

    // ========== Macro/Alias tokens ==========
    val MACRO: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_MACRO",
        DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL
    )

    // ========== Keywords and operators ==========
    val KEYWORD: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_KEYWORD",
        DefaultLanguageHighlighterColors.KEYWORD
    )
    val MODIFIER: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_MODIFIER",
        DefaultLanguageHighlighterColors.KEYWORD
    )
    val OPERATOR: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_OPERATOR",
        DefaultLanguageHighlighterColors.OPERATION_SIGN
    )

    // ========== Literals ==========
    val STRING: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_STRING",
        DefaultLanguageHighlighterColors.STRING
    )
    val NUMBER: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_NUMBER",
        DefaultLanguageHighlighterColors.NUMBER
    )
    val REGEXP: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_REGEXP",
        DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE
    )

    // ========== Comments and Documentation ==========
    val COMMENT: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_COMMENT",
        DefaultLanguageHighlighterColors.LINE_COMMENT
    )
    val DOCUMENTATION: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_DOCUMENTATION",
        DefaultLanguageHighlighterColors.DOC_COMMENT
    )

    // ========== Annotations and decorators ==========
    val DECORATOR: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_DECORATOR",
        DefaultLanguageHighlighterColors.METADATA
    )

    // ========== Deprecated variants ==========
    val DEPRECATED_FUNCTION: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_DEPRECATED_FUNCTION",
        DefaultLanguageHighlighterColors.IDENTIFIER
    )
    val DEPRECATED_METHOD: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_DEPRECATED_METHOD",
        DefaultLanguageHighlighterColors.IDENTIFIER
    )
    val DEPRECATED_PROPERTY: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_DEPRECATED_PROPERTY",
        DefaultLanguageHighlighterColors.IDENTIFIER
    )

    // ========== Other modifiers ==========
    val MODIFICATION: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "LUA_MODIFICATION",
        DefaultLanguageHighlighterColors.REASSIGNED_LOCAL_VARIABLE
    )

}