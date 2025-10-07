package com.github.davidseptimus.lualsintellijplugin.schema

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.jsonSchema.extension.JsonSchemaFileProvider
import com.jetbrains.jsonSchema.extension.JsonSchemaProviderFactory
import com.jetbrains.jsonSchema.extension.SchemaType

class LuarcJsonSchemaProviderFactory : JsonSchemaProviderFactory {
    override fun getProviders(project: Project): List<JsonSchemaFileProvider> {
        return listOf(LuarcJsonSchemaFileProvider())
    }
}

class LuarcJsonSchemaFileProvider : JsonSchemaFileProvider {
    override fun isAvailable(file: VirtualFile): Boolean {
        return file.name == ".luarc.json"
    }

    override fun getName(): String = "Lua Configuration"

    override fun getSchemaFile(): VirtualFile? = null

    override fun getSchemaType(): SchemaType = SchemaType.remoteSchema

    override fun getRemoteSource(): String = "https://raw.githubusercontent.com/sumneko/vscode-lua/master/setting/schema.json"
}