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
        "declaration",
        "definition",
        "readonly",
        "static",
        "deprecated",
        "abstract",
        "async",
        "modification",
        "documentation",
        "defaultLibrary",
        "global"
    )

    override fun getTextAttributesKey(tokenType: String, modifiers: List<String>): TextAttributesKey? {
        // Handle modifier-specific variants for different token types
        return when (tokenType) {
            "variable" -> getVariableAttributeKey(modifiers)
            "function" -> getFunctionAttributeKey(modifiers)
            "property" -> getPropertyAttributeKey(modifiers)
            "method" -> getMethodAttributeKey(modifiers)
            "comment" -> if ("documentation" in modifiers) {
                LuaTextAttributesKeys.DOCUMENTATION
            } else {
                LuaTextAttributesKeys.COMMENT
            }
            // Base types without modifiers
            "namespace" -> LuaTextAttributesKeys.NAMESPACE
            "type" -> LuaTextAttributesKeys.TYPE
            "class" -> LuaTextAttributesKeys.CLASS
            "enum" -> LuaTextAttributesKeys.ENUM
            "interface" -> LuaTextAttributesKeys.INTERFACE
            "struct" -> LuaTextAttributesKeys.STRUCT
            "typeParameter" -> LuaTextAttributesKeys.TYPE_PARAMETER
            "parameter" -> LuaTextAttributesKeys.PARAMETER
            "enumMember" -> LuaTextAttributesKeys.ENUM_MEMBER
            "event" -> LuaTextAttributesKeys.EVENT
            "macro" -> LuaTextAttributesKeys.MACRO
            "keyword" -> LuaTextAttributesKeys.KEYWORD
            "modifier" -> LuaTextAttributesKeys.MODIFIER
            "string" -> LuaTextAttributesKeys.STRING
            "number" -> LuaTextAttributesKeys.NUMBER
            "regexp" -> LuaTextAttributesKeys.REGEXP
            "operator" -> LuaTextAttributesKeys.OPERATOR
            "decorator" -> LuaTextAttributesKeys.DECORATOR
            else -> {
                System.err.println("Unknown token type: $tokenType")
                null
            }
        }
    }

    /**
     * Returns the appropriate text attribute key for variables based on modifiers.
     * Priority: global > readonly > defaultLibrary > base
     */
    private fun getVariableAttributeKey(modifiers: List<String>): TextAttributesKey {
        return when {
            "global" in modifiers -> LuaTextAttributesKeys.GLOBAL_VARIABLE
            "readonly" in modifiers -> LuaTextAttributesKeys.READONLY_VARIABLE
            "defaultLibrary" in modifiers -> LuaTextAttributesKeys.DEFAULT_LIBRARY_VARIABLE
            else -> LuaTextAttributesKeys.VARIABLE
        }
    }

    /**
     * Returns the appropriate text attribute key for functions based on modifiers.
     * Priority: deprecated > global > defaultLibrary > base
     */
    private fun getFunctionAttributeKey(modifiers: List<String>): TextAttributesKey {
        return when {
            "deprecated" in modifiers -> LuaTextAttributesKeys.DEPRECATED_FUNCTION
            "global" in modifiers -> LuaTextAttributesKeys.GLOBAL_FUNCTION
            "defaultLibrary" in modifiers -> LuaTextAttributesKeys.DEFAULT_LIBRARY_FUNCTION
            else -> LuaTextAttributesKeys.FUNCTION
        }
    }

    /**
     * Returns the appropriate text attribute key for properties based on modifiers.
     * Priority: deprecated > readonly > static > base
     */
    private fun getPropertyAttributeKey(modifiers: List<String>): TextAttributesKey {
        return when {
            "deprecated" in modifiers -> LuaTextAttributesKeys.DEPRECATED_PROPERTY
            "readonly" in modifiers -> LuaTextAttributesKeys.READONLY_PROPERTY
            "static" in modifiers -> LuaTextAttributesKeys.STATIC_PROPERTY
            else -> LuaTextAttributesKeys.PROPERTY
        }
    }

    /**
     * Returns the appropriate text attribute key for methods based on modifiers.
     * Priority: deprecated > static > base
     */
    private fun getMethodAttributeKey(modifiers: List<String>): TextAttributesKey {
        return when {
            "deprecated" in modifiers -> LuaTextAttributesKeys.DEPRECATED_METHOD
            "static" in modifiers -> LuaTextAttributesKeys.STATIC_METHOD
            else -> LuaTextAttributesKeys.METHOD
        }
    }

}
