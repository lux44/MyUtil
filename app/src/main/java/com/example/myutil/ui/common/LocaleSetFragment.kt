package com.example.myutil.ui.common

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myutil.utils.LanguageUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale

@AndroidEntryPoint
abstract class LocaleSetFragment: Fragment() {

    private val baseViewModel: BaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocale(requireActivity().baseContext)
    }

    private fun setLocale(context: Context) {
        val language = getLanguage()
        val config = context.resources?.configuration
        val sysLocale: Locale
        if (config != null) {
            sysLocale = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                AppContextWrapper.getSystemLocale(config)
            } else {
                AppContextWrapper.getSystemLocaleLegacy(config)
            }
            if (language.isNotEmpty() && !sysLocale.language.equals(language)) {
                val locale = LanguageUtils.getLanguageLocale(language)
                Locale.setDefault(locale)
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                    AppContextWrapper.setSystemLocale(config, locale)
                } else {
                    AppContextWrapper.setSystemLocaleLegacy(config, locale)
                }
                Timber.d("lang = $language, $locale, $config")
            }

            @Suppress("DEPRECATION")
            context.resources
                .updateConfiguration(config, context.resources.displayMetrics)
        }
    }

    private fun getLanguage(): String {
        var language :String? = ""
        lifecycleScope.launch {
            language = baseViewModel.getLanguageCode()
        }

        val isEmptyLang = language.isNullOrEmpty()
        if (isEmptyLang) {
            language = Locale.getDefault().language
        }

        val locale: Locale = LanguageUtils.getLanguageLocale(language)
        if (isEmptyLang) {
            setPreferenceLanguage(locale)
        }
        return language?:LanguageUtils.ENGLISH
    }

    private fun setPreferenceLanguage(locale: Locale) {
        val langStr = locale.toString()
        var lang = "en"
        when (langStr.lowercase()) {
            "ko_kr", "ko" -> {
                lang = LanguageUtils.KOREAN
            }
            "en" -> {
                lang = LanguageUtils.ENGLISH
            }
            "ja" -> {
                lang = LanguageUtils.JAPANESE
            }
            "zh_cn" -> {
                lang = LanguageUtils.CHINESE_SIMPLIFIED
            }
            //"zh_TW_#Hant" ,"zh_HK_#Hant"
            "zh_tw" -> {
                lang = LanguageUtils.CHINESE_TRADITIONAL
            }
            "in_id", "in" -> {
                lang = LanguageUtils.INDONESIAN
            }
            "es_es" -> {
                lang = LanguageUtils.SPANISH
            }
            "fr_fr" -> {
                lang = LanguageUtils.FRENCH
            }
            "ru_ru", "ru" -> {
                lang = LanguageUtils.RUSSIAN
            }
            "de" -> {
                lang = LanguageUtils.GERMAN
            }
            "pt" -> {
                lang = LanguageUtils.PORTUGUESE
            }
            "vi" -> {
                lang = LanguageUtils.VIETNAMESE
            }
            "th" -> {
                lang = LanguageUtils.THAI
            }
            "ar" -> {
                lang = LanguageUtils.ARABIC
            }
            "pl" -> {
                lang = LanguageUtils.POLISH
            }
            "it" -> {
                lang = LanguageUtils.ITALIAN
            }
            "hi" -> {
                lang = LanguageUtils.HINDI
            }
        }
        lifecycleScope.launch {
            baseViewModel.setLanguageCode(lang)
        }
    }
}