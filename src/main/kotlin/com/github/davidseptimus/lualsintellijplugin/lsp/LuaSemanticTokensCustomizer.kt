package com.github.davidseptimus.lualsintellijplugin.lsp

import com.github.davidseptimus.lualsintellijplugin.LuaFileType
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.platform.lsp.api.customization.LspSemanticTokensSupport
import com.intellij.psi.PsiFile

open class LuaSemanticTokensCustomizer : LspSemanticTokensSupport() {

    override fun shouldAskServerForSemanticTokens(psiFile: PsiFile): Boolean {
        return true
    }

    override val tokenModifiers: List<String> = listOf(
        "declaration", "definition", "readonly", "static", "deprecated", "abstract", "async", "modification", "documentation", "defaultLibrary", "global"
    )
    override val tokenTypes: List<String> = listOf(
        "namespace",     // 0
        "type",          // 1
        "class",         // 2
        "enum",          // 3
        "interface",     // 4
        "struct",        // 5
        "typeParameter", // 6
        "parameter",     // 7
        "variable",      // 8
        "property",      // 9
        "enumMember",    // 10
        "event",         // 11
        "function",      // 12
        "method",        // 13
        "macro",         // 14
        "keyword",       // 15
        "modifier",      // 16
        "comment",       // 17
        "string",        // 18
        "number",        // 19
        "regexp",        // 20
        "operator",      // 21
        "decorator"      // 22
    )

    override fun getTextAttributesKey(tokenType: String, modifiers: List<String>): TextAttributesKey? =
        when (tokenType) {
            "namespace"     -> LuaTextAttributesKeys.NAMESPACE
            "type"          -> LuaTextAttributesKeys.TYPE
            "class"         -> LuaTextAttributesKeys.CLASS
            "enum"          -> LuaTextAttributesKeys.ENUM
            "interface"     -> LuaTextAttributesKeys.INTERFACE
            "struct"        -> LuaTextAttributesKeys.STRUCT
            "typeParameter" -> LuaTextAttributesKeys.TYPE_PARAMETER
            "parameter"     -> LuaTextAttributesKeys.PARAMETER
            "variable"      -> LuaTextAttributesKeys.VARIABLE
            "property"      -> LuaTextAttributesKeys.PROPERTY
            "enumMember"    -> LuaTextAttributesKeys.ENUM_MEMBER
            "event"         -> LuaTextAttributesKeys.EVENT
            "function"      -> LuaTextAttributesKeys.FUNCTION
            "method"        -> LuaTextAttributesKeys.METHOD
            "macro"         -> LuaTextAttributesKeys.MACRO
            "keyword"       -> LuaTextAttributesKeys.KEYWORD
            "modifier"      -> LuaTextAttributesKeys.MODIFIER
            "comment"       -> LuaTextAttributesKeys.COMMENT
            "string"        -> LuaTextAttributesKeys.STRING
            "number"        -> LuaTextAttributesKeys.NUMBER
            "regexp"        -> LuaTextAttributesKeys.REGEXP
            "operator"      -> LuaTextAttributesKeys.OPERATOR
            "decorator"     -> LuaTextAttributesKeys.DECORATOR
            else -> {
                System.err.println("Unknown token type: $tokenType")
                null
            }
        }

}
