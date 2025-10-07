package com.github.davidseptimus.lualsintellijplugin.settings

import com.intellij.DynamicBundle
import java.util.*

object LuaLocale {
    const val EN_US = "en-us"
    const val PT_BR = "pt-br"
    const val ZH_CN = "zh-cn"
    const val ZH_TW = "zh-tw"

    val SUPPORTED_LOCALES = listOf(EN_US, PT_BR, ZH_CN, ZH_TW)

    /**
     * Gets the locale to use for lua-language-server.
     * @param userConfiguredLocale The locale from user settings (empty string means use IDE language)
     * @return The locale string to pass to --locale flag
     */
    fun getLocale(userConfiguredLocale: String): String {
        // If user explicitly configured a locale, use it
        if (userConfiguredLocale.isNotEmpty() && SUPPORTED_LOCALES.contains(userConfiguredLocale)) {
            return userConfiguredLocale
        }

        // Otherwise, try to map IDE locale to LSP locale
        return mapIdeLocaleToLspLocale(DynamicBundle.getLocale())
    }

    private fun mapIdeLocaleToLspLocale(ideLocale: Locale): String {
        val language = ideLocale.language.lowercase()
        val country = ideLocale.country.lowercase()

        return when {
            language == "pt" && country == "br" -> PT_BR
            language == "zh" && country == "cn" -> ZH_CN
            language == "zh" && country == "tw" -> ZH_TW
            language == "en" -> EN_US
            else -> EN_US // Fallback to English
        }
    }

    /**
     * Get display name for locale selection in settings UI
     */
    fun getDisplayName(locale: String): String {
        return when (locale) {
            EN_US -> "English - United States"
            PT_BR -> "Portuguese - Brazil"
            ZH_CN -> "Chinese - People's Republic of China"
            ZH_TW -> "Chinese - Taiwan"
            "" -> "Use IDE Language"
            else -> locale
        }
    }
}