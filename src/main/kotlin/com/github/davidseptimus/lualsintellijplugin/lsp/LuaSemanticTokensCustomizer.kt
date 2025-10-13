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
        "declaration", "definition", "readonly", "static", "deprecated", "abstract", "async", "modification", "documentation", "defaultLibrary", "global", "local"
    )
    override val tokenTypes: List<String> = listOf(
       "if", "then", "keyword", "end", "else", "end", "namespace", "type", "class", "enum", "interface", "struct", "typeParameter", "parameter", "variable", "property", "enumMember", "event", "function", "method", "macro", "keyword", "modifier", "comment", "string", "number", "regexp", "operator", "decorator"
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
            "local" -> LuaTextAttributesKeys.KEYWORD
            else -> {
                System.err.println("Unknown token type: $tokenType")
                LuaTextAttributesKeys.STRING
            }
        }

}
