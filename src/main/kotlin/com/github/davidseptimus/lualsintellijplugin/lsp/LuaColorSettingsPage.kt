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
        // ========== Types ==========
        AttributesDescriptor("Type//Namespace", LuaTextAttributesKeys.NAMESPACE),
        AttributesDescriptor("Type//Type reference", LuaTextAttributesKeys.TYPE),
        AttributesDescriptor("Type//Class", LuaTextAttributesKeys.CLASS),
        AttributesDescriptor("Type//Enum", LuaTextAttributesKeys.ENUM),
        AttributesDescriptor("Type//Interface", LuaTextAttributesKeys.INTERFACE),
        AttributesDescriptor("Type//Struct", LuaTextAttributesKeys.STRUCT),
        AttributesDescriptor("Type//Type parameter", LuaTextAttributesKeys.TYPE_PARAMETER),

        // ========== Variables ==========
        AttributesDescriptor("Variables//Local variable", LuaTextAttributesKeys.VARIABLE),
        AttributesDescriptor("Variables//Global variable", LuaTextAttributesKeys.GLOBAL_VARIABLE),
        AttributesDescriptor("Variables//Readonly variable", LuaTextAttributesKeys.READONLY_VARIABLE),
        AttributesDescriptor("Variables//Default library variable", LuaTextAttributesKeys.DEFAULT_LIBRARY_VARIABLE),
        AttributesDescriptor("Variables//Parameter", LuaTextAttributesKeys.PARAMETER),

        // ========== Properties ==========
        AttributesDescriptor("Properties//Property", LuaTextAttributesKeys.PROPERTY),
        AttributesDescriptor("Properties//Readonly property", LuaTextAttributesKeys.READONLY_PROPERTY),
        AttributesDescriptor("Properties//Static property", LuaTextAttributesKeys.STATIC_PROPERTY),
        AttributesDescriptor("Properties//Deprecated property", LuaTextAttributesKeys.DEPRECATED_PROPERTY),

        // ========== Functions ==========
        AttributesDescriptor("Functions//Function", LuaTextAttributesKeys.FUNCTION),
        AttributesDescriptor("Functions//Global function", LuaTextAttributesKeys.GLOBAL_FUNCTION),
        AttributesDescriptor("Functions//Default library function", LuaTextAttributesKeys.DEFAULT_LIBRARY_FUNCTION),
        AttributesDescriptor("Functions//Deprecated function", LuaTextAttributesKeys.DEPRECATED_FUNCTION),

        // ========== Methods ==========
        AttributesDescriptor("Methods//Method", LuaTextAttributesKeys.METHOD),
        AttributesDescriptor("Methods//Static method", LuaTextAttributesKeys.STATIC_METHOD),
        AttributesDescriptor("Methods//Deprecated method", LuaTextAttributesKeys.DEPRECATED_METHOD),

        // ========== Other Identifiers ==========
        AttributesDescriptor("Other//Enum member", LuaTextAttributesKeys.ENUM_MEMBER),
        AttributesDescriptor("Other//Event", LuaTextAttributesKeys.EVENT),
        AttributesDescriptor("Other//Macro (type alias)", LuaTextAttributesKeys.MACRO),

        // ========== Keywords and Operators ==========
        AttributesDescriptor("Syntax//Keyword", LuaTextAttributesKeys.KEYWORD),
        AttributesDescriptor("Syntax//Modifier", LuaTextAttributesKeys.MODIFIER),
        AttributesDescriptor("Syntax//Operator", LuaTextAttributesKeys.OPERATOR),

        // ========== Literals ==========
        AttributesDescriptor("Literals//String", LuaTextAttributesKeys.STRING),
        AttributesDescriptor("Literals//Number", LuaTextAttributesKeys.NUMBER),
        AttributesDescriptor("Literals//Regexp", LuaTextAttributesKeys.REGEXP),

        // ========== Comments and Documentation ==========
        AttributesDescriptor("Comments//Comment", LuaTextAttributesKeys.COMMENT),
        AttributesDescriptor("Comments//Documentation", LuaTextAttributesKeys.DOCUMENTATION),
        AttributesDescriptor("Comments//Decorator (annotation)", LuaTextAttributesKeys.DECORATOR),

        // ========== Other ==========
        AttributesDescriptor("Other//Modification", LuaTextAttributesKeys.MODIFICATION),
    )

    override fun getColorDescriptors() = emptyArray<com.intellij.openapi.options.colors.ColorDescriptor>()

    override fun getDemoText(): String = """
<documentation>--- Documentation comment</documentation>
<documentation>---</documentation><decorator>@class</decorator> <class>MyClass</class>
<documentation>---</documentation><decorator>@field</decorator> <property>value</property> <type>string</type> <comment>-- Instance property</comment>
<documentation>---</documentation><decorator>@field</decorator> <readonly_property>readonlyProp</readonly_property> <type>string</type> <comment>-- Readonly property</comment>
<keyword>local</keyword> <class>MyClass</class> <operator>=</operator> {}

<documentation>---</documentation><decorator>@enum</decorator> <enum>Status</enum>
<keyword>local</keyword> <enum>Status</enum> <operator>=</operator> {
    <enum_member>PENDING</enum_member> <operator>=</operator> <number>1</number>,
    <enum_member>ACTIVE</enum_member> <operator>=</operator> <number>2</number>,
}

<documentation>---</documentation><decorator>@alias</decorator> <macro>StringOrNumber</macro> <type>string</type><operator>|</operator><type>number</type>

<comment>-- Variables and Constants</comment>
<keyword>local</keyword> <variable>localVar</variable> <operator>=</operator> <string>"local"</string>
<keyword>local</keyword> <readonly>CONSTANT <type_parameter><const></type_parameter> </readonly> <operator>=</operator> <number>100</number>
<global>globalVar</global> <operator>=</operator> <string>"global"</string>

<comment>-- Built-in variables (default library)</comment>
<default_library_variable>_G</default_library_variable>.<global>newGlobal</global> <operator>=</operator> <keyword>true</keyword>
<default_library_variable>_VERSION</default_library_variable> <comment>-- Lua version</comment>

<comment>-- Functions</comment>
<documentation>---</documentation><decorator>@param</decorator> <parameter>items</parameter> <type>table</type>
<documentation>---</documentation><decorator>@param</decorator> <parameter>callback</parameter> <type>function</type>
<documentation>---</documentation><decorator>@return</decorator> <type>table</type>
<keyword>local</keyword> <keyword>function</keyword> <function>filter</function>(<parameter>items</parameter>, <parameter>callback</parameter>)
    <keyword>local</keyword> <variable>result</variable> <operator>=</operator> {}
    <keyword>for</keyword> <variable>i</variable> <operator>=</operator> <number>1</number>, <operator>#</operator><parameter>items</parameter> <keyword>do</keyword>
        <keyword>if</keyword> <parameter>callback</parameter>(<parameter>items</parameter>[<variable>i</variable>]) <keyword>then</keyword>
            <variable>result</variable>[<operator>#</operator><variable>result</variable> <operator>+</operator> <number>1</number>] <operator>=</operator> <parameter>items</parameter>[<variable>i</variable>]
        <keyword>end</keyword>
    <keyword>end</keyword>
    <keyword>return</keyword> <variable>result</variable>
<keyword>end</keyword>

<comment>-- Global function</comment>
<keyword>function</keyword> <global_function>globalHelper</global_function>(<parameter>x</parameter>)
    <keyword>return</keyword> <parameter>x</parameter> <operator>*</operator> <number>2</number>
<keyword>end</keyword>

<comment>-- Built-in functions (default library)</comment>
<default_library>print</default_library>(<string>"Hello, World!"</string>)
<default_library>table</default_library>.<default_library>insert</default_library>({}, <number>1</number>)
<default_library>math</default_library>.<default_library>sqrt</default_library>(<number>16</number>)

<comment>-- Methods</comment>
<keyword>function</keyword> <class>MyClass</class><operator>:</operator><method>setValue</method>(<keyword>self</keyword>, <parameter>value</parameter>)
    <keyword>self</keyword>.<property>value</property> <operator>=</operator> <parameter>value</parameter>
<keyword>end</keyword>

<documentation>---</documentation><decorator>@deprecated</decorator> Use setValue instead
<keyword>function</keyword> <class>MyClass</class><operator>:</operator><deprecated_method>setOld</deprecated_method>(<keyword>self</keyword>, <parameter>value</parameter>)
    <keyword>self</keyword>.<property>value</property> <operator>=</operator> <parameter>value</parameter>
<keyword>end</keyword>

<comment>-- Numbers and Strings</comment>
<keyword>local</keyword> <variable>int</variable> <operator>=</operator> <number>42</number>
<keyword>local</keyword> <variable>hex</variable> <operator>=</operator> <number>0xFF</number>
<keyword>local</keyword> <variable>float</variable> <operator>=</operator> <number>3.14159</number>
<keyword>local</keyword> <variable>str</variable> <operator>=</operator> <string>"text"</string>
<keyword>local</keyword> <variable>multiline</variable> <operator>=</operator> <string>[[multi-line
text]]</string>

<comment>-- Control flow</comment>
<keyword>if</keyword> <variable>int</variable> <operator>></operator> <number>0</number> <keyword>then</keyword>
    <default_library>print</default_library>(<string>"positive"</string>)
<keyword>elseif</keyword> <variable>int</variable> <operator><</operator> <number>0</number> <keyword>then</keyword>
    <default_library>print</default_library>(<string>"negative"</string>)
<keyword>else</keyword>
    <default_library>print</default_library>(<string>"zero"</string>)
<keyword>end</keyword>

<keyword>for</keyword> <variable>i</variable> <operator>=</operator> <number>1</number>, <number>10</number> <keyword>do</keyword>
    <default_library>print</default_library>(<variable>i</variable>)
<keyword>end</keyword>

<comment>-- Labels</comment>
<keyword>goto</keyword> <struct>skip</struct>
<operator>::</operator><struct>skip</struct><operator>::</operator>
"""

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> = mapOf(
        // Base types
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
        "type_parameter" to LuaTextAttributesKeys.TYPE_PARAMETER,

        // Modified variants
        "global" to LuaTextAttributesKeys.GLOBAL_VARIABLE,
        "global_function" to LuaTextAttributesKeys.GLOBAL_FUNCTION,
        "readonly" to LuaTextAttributesKeys.READONLY_VARIABLE,
        "readonly_property" to LuaTextAttributesKeys.READONLY_PROPERTY,
        "static_property" to LuaTextAttributesKeys.STATIC_PROPERTY,
        "static_method" to LuaTextAttributesKeys.STATIC_METHOD,
        "default_library" to LuaTextAttributesKeys.DEFAULT_LIBRARY_FUNCTION,
        "default_library_variable" to LuaTextAttributesKeys.DEFAULT_LIBRARY_VARIABLE,
        "deprecated_function" to LuaTextAttributesKeys.DEPRECATED_FUNCTION,
        "deprecated_method" to LuaTextAttributesKeys.DEPRECATED_METHOD,
        "deprecated_property" to LuaTextAttributesKeys.DEPRECATED_PROPERTY,
        "documentation" to LuaTextAttributesKeys.DOCUMENTATION,
        "modification" to LuaTextAttributesKeys.MODIFICATION,
    )
}
