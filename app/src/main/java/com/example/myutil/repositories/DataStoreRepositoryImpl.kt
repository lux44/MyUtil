package com.example.myutil.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import javax.inject.Inject

private const val PREFERENCES_NAME = "preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreRepositoryImpl @Inject constructor(private val context: Context): DataStoreRepository {
    override suspend fun putString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun clearString(key: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences.remove(preferencesKey)
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun putLong(key: String, value: Long) {
        val preferencesKey = longPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun putStringArray(key: String, value: ArrayList<String>) {
        val jsonArr = JSONArray()
        for (a in value) {
            jsonArr.put(a)
        }
        val setItem = jsonArr.toString()
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = setItem
        }
    }

    override suspend fun getString(key: String): String? {
        val preferencesKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun getInt(key: String): Int? {
        val preferencesKey = intPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun getLong(key: String): Long? {
        val preferencesKey = longPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun getBoolean(key: String): Boolean? {
        val preferencesKey = booleanPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun getStringFlow(key: String): Flow<String?> =
        context.dataStore.data.map { preferences->
            preferences[stringPreferencesKey(key)]
        }

    override suspend fun getBooleanFlow(key: String): Flow<Boolean?> =
        context.dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)] ?: false
        }

    override suspend fun getIntFlow(key: String): Flow<Int?> =
        context.dataStore.data.map { preferences->
            preferences[intPreferencesKey(key)]
        }

    override suspend fun getStringArray(key: String): ArrayList<String>? {
        val preferencesKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        val item = preferences[preferencesKey] ?: return null
        val resultArray = ArrayList<String>()
        val jsonItem = JSONArray(item)
        for (a in 0 until jsonItem.length()) {
            if (jsonItem.optString(a) == "") continue
            resultArray.add(jsonItem.optString(a))
        }
        return resultArray
    }
}

class DataStoreKey {
    companion object {
        const val PREF_KEY_LANGUAGE_CODE = "language_code"
        const val PREF_KEY_TRANSLATE_LANGUAGE_CODE = "translate_language_code"
        const val PREF_KEY_ACCESS_TOKEN = "access_token"
        const val PREF_KEY_REFRESH_ACCESS_TOKEN = "refresh_access_token"
        const val PREF_KEY_REFRESH_TOKEN_PUBLISH_TIME = "refresh_token_publish_date"
        const val PREF_KEY_CONFIG_APP_VERSION = "current_version"
        const val PREF_KEY_UID = "uid"
        const val PREF_KEY_IS_LOGINED = "logined"
        const val PREF_KEY_LAST_LOGIN_TYPE = "last_login_type"
        const val PREF_KEY_IS_PERMISSION_CHECKED = "permission_checked"
        const val PREF_KEY_CLUBSEARCHWORDLIST = "club_search_word_list_key"
        const val PREF_KEY_CLUBDETAILSEARCHWORDLIST = "club_detail_search_word_list_key"
        const val PREF_KEY_COMMUNITYSEARCHWORDLIST = "community_search_word_list_key"
        const val PREF_KEY_COMMUNITY_FAVORITE_SORT_TYPE = "community_favorite_sort_type_key"
        const val PREF_KEY_RECENT_NEWS_SEARCH = "recent_news_search"
        const val PREF_KEY_FCM_TOKEN = "fcm_token"
        const val PREF_KEY_IS_FIRST_PROFILE_COMPLETE = "first_profile_complete"
        const val PREF_KEY_IS_FIRST_SELECT_INTEREST = "first_select_interest"
        const val PREF_KEY_BOARD_LIST_FAVORITE_STATE = "board_list_favorite_state"
        const val PREF_KEY_MY_CLUBS_IS_FAVORITE = "my_clubs_favorite"
        const val PREF_KEY_API_URL = "api_url"
        const val PREF_KEY_CLOUDFLARE_URL = "cloud_flare_url"
        const val PREF_KEY_SYSTEM_COUNTRY = "system_country"
        const val PREFERENCE_KEY_MINUTE_SORTING = "minute_sorting"
        const val PREFERENCE_KEY_MINUTE_PROFILE_SORTING = "minute_profile_sorting"
        const val PREF_KEY_NEW_ALIM_MESSAGE = "new_alim_message"
        const val PREF_KEY_NEW_CHAT_ALIM_MESSAGE = "new_chat_alim_message"
        const val PREFERENCE_KEY_USER_LOGIN_ID = "user_login_id"
        const val PREFERENCE_KEY_USER_PROFILE_IMAGE = "user_profile_image"
        const val PREFERENCE_KEY_USER_NICKNAME = "user_nickname"
        const val PREFERENCE_KEY_MINUTE_SEARCH_WORD_LIST = "minute_search_word_list"
        const val PREF_KEY_MAINPOPUP_DONT_SHOW_DAY = "mainpopup_dontshow_day"
        const val PREF_KEY_MAINPOPUP_SHOWED_TIME = "mainpopup_showed_time"
        const val PREF_KEY_MAINPOPUP_LAST_INDX = "mainpopup_last_Index"
        const val PREF_KEY_MARKETING_PUSH_ON = "MARKETING_PUSH_ON"
        const val PREF_KEY_MAIN_ACTIVITY_BACKGROUND_START_TIME = "main_activity_background_start_time"
        const val PREF_KEY_AUTO_TRANSLATE = "auto_translate"
        const val PREF_KEY_IS_FIRST_RUN = "is_first_run"
        const val PREF_KEY_HOME_SEARCH_TEXT_LIST = "home_search_text_list"
    }
}
