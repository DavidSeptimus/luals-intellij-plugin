package com.github.davidseptimus.lualsintellijplugin.lsp

import com.github.davidseptimus.lualsintellijplugin.LuaFileType
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.PlainSyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class LuaColorSettingsPage : ColorSettingsPage {
    override fun getDisplayName(): String = "Lua"
    override fun getIcon(): Icon = LuaFileType.icon

    override fun getHighlighter(): SyntaxHighlighter = PlainSyntaxHighlighter()

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
<comment>-- Comment demonstrating Lua syntax highlighting</comment>

<comment>---</comment><decorator>@class</decorator> <class>MyClass</class>
<comment>---</comment><decorator>@field</decorator> <property>value</property> <type>string</type>
<keyword>local</keyword> <class>MyClass</class> <operator>=</operator> {}

<comment>---</comment><decorator>@enum</decorator> <enum>Status</enum>
<keyword>local</keyword> <enum>Status</enum> <operator>=</operator> {
    <enum_member>PENDING</enum_member> <operator>=</operator> <number>1</number>,
    <enum_member>ACTIVE</enum_member> <operator>=</operator> <number>2</number>,
}

<comment>---</comment><decorator>@alias</decorator> <macro>StringOrNumber</macro> <type>string</type><operator>|</operator><type>number</type>

<comment>---</comment><decorator>@param</decorator> <parameter>items</parameter> <type>table</type>
<comment>---</comment><decorator>@param</decorator> <parameter>callback</parameter> <type>function</type>
<comment>---</comment><decorator>@return</decorator> <type>table</type>
<keyword>local</keyword> <keyword>function</keyword> <function>filter</function>(<parameter>items</parameter>, <parameter>callback</parameter>)
    <keyword>local</keyword> <variable>result</variable> <operator>=</operator> {}
    <keyword>for</keyword> <variable>i</variable> <operator>=</operator> <number>1</number>, <operator>#</operator><parameter>items</parameter> <keyword>do</keyword>
        <keyword>if</keyword> <parameter>callback</parameter>(<parameter>items</parameter>[<variable>i</variable>]) <keyword>then</keyword>
            <variable>result</variable>[<operator>#</operator><variable>result</variable> <operator>+</operator> <number>1</number>] <operator>=</operator> <parameter>items</parameter>[<variable>i</variable>]
        <keyword>end</keyword>
    <keyword>end</keyword>
    <keyword>return</keyword> <variable>result</variable>
<keyword>end</keyword>

<comment>---</comment><decorator>@param</decorator> <keyword>self</keyword> <class>MyClass</class>
<comment>---</comment><decorator>@deprecated</decorator> Use setNew instead
<keyword>function</keyword> <class>MyClass</class><operator>:</operator><method>setOld</method>(<keyword>self</keyword>, <parameter>value</parameter>)
    <keyword>self</keyword>.<property>value</property> <operator>=</operator> <parameter>value</parameter>
<keyword>end</keyword>

<comment>-- Built-in functions (default library)</comment>
<default_library>print</default_library>(<string>"Hello, World!"</string>)
<default_library>table</default_library>.<default_library>insert</default_library>({}, <number>1</number>)
<default_library>math</default_library>.<default_library>sqrt</default_library>(<number>16</number>)

<comment>-- Global variables</comment>
<global>myGlobalVar</global> <operator>=</operator> <string>"test"</string>

<comment>-- Numbers</comment>
<keyword>local</keyword> <variable>int</variable> <operator>=</operator> <number>42</number>
<keyword>local</keyword> <variable>hex</variable> <operator>=</operator> <number>0xFF</number>
<keyword>local</keyword> <variable>float</variable> <operator>=</operator> <number>3.14159</number>

<comment>-- Strings</comment>
<keyword>local</keyword> <variable>str1</variable> <operator>=</operator> <string>"double quoted"</string>
<keyword>local</keyword> <variable>str2</variable> <operator>=</operator> <string>'single quoted'</string>
<keyword>local</keyword> <variable>multiline</variable> <operator>=</operator> <string>[[multi
line]]</string>

<comment>-- Keywords and operators</comment>
<keyword>if</keyword> <variable>int</variable> <operator>></operator> <number>0</number> <keyword>then</keyword>
    <keyword>local</keyword> <variable>x</variable> <operator>=</operator> <keyword>true</keyword>
<keyword>elseif</keyword> <variable>int</variable> <operator><</operator> <number>0</number> <keyword>then</keyword>
    <keyword>local</keyword> <variable>y</variable> <operator>=</operator> <keyword>false</keyword>
<keyword>else</keyword>
    <keyword>local</keyword> <variable>z</variable> <operator>=</operator> <keyword>nil</keyword>
<keyword>end</keyword>

<keyword>while</keyword> <variable>int</variable> <operator>></operator> <number>0</number> <keyword>do</keyword>
    <variable>int</variable> <operator>=</operator> <variable>int</variable> <operator>-</operator> <number>1</number>
    <keyword>if</keyword> <variable>int</variable> <operator>==</operator> <number>5</number> <keyword>then</keyword> <keyword>break</keyword> <keyword>end</keyword>
<keyword>end</keyword>

<comment>-- Goto and labels</comment>
<keyword>goto</keyword> <struct>skip</struct>
<operator>::</operator><struct>skip</struct><operator>::</operator>

<comment>-- Readonly variable</comment>
<keyword>local</keyword> <readonly>CONSTANT</readonly> <operator><const></operator> <operator>=</operator> <number>100</number>
"""

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> = mapOf(
        "namespace" to LuaTextAttributesKeys.NAMESPACE,
        "type" to LuaTextAttributesKeys.TYPE,
        "class" to LuaTextAttributesKeys.CLASS,
        "enum" to LuaTextAttributesKeys.ENUM,
        "interface" to LuaTextAttributesKeys.INTERFACE,
        "struct" to LuaTextAttributesKeys.STRUCT,
        "parameter" to LuaTextAttributesKeys.PARAMETER,
        "variable" to LuaTextAttributesKeys.VARIABLE,
        "property" to LuaTextAttributesKeys.PROPERTY,
        "enum_member" to LuaTextAttributesKeys.ENUM_MEMBER,
        "event" to LuaTextAttributesKeys.EVENT,
        "function" to LuaTextAttributesKeys.FUNCTION,
        "method" to LuaTextAttributesKeys.METHOD,
        "macro" to LuaTextAttributesKeys.MACRO,
        "keyword" to LuaTextAttributesKeys.KEYWORD,
        "modifier" to LuaTextAttributesKeys.MODIFIER,
        "comment" to LuaTextAttributesKeys.COMMENT,
        "string" to LuaTextAttributesKeys.STRING,
        "number" to LuaTextAttributesKeys.NUMBER,
        "operator" to LuaTextAttributesKeys.OPERATOR,
        "decorator" to LuaTextAttributesKeys.DECORATOR,
        "readonly" to LuaTextAttributesKeys.READONLY,
        "static" to LuaTextAttributesKeys.STATIC,
        "deprecated" to LuaTextAttributesKeys.DEPRECATED,
        "default_library" to LuaTextAttributesKeys.DEFAULT_LIBRARY,
        "global" to LuaTextAttributesKeys.GLOBAL,
    )
}
