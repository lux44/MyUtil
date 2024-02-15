package com.example.myutil.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myutil.repositories.DataStoreKey
import com.example.myutil.repositories.DataStoreRepository
import com.example.myutil.repositories.UserInfoRepository
import com.example.myutil.utils.ModeChanger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeTabViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    var appMode: Int = ModeChanger.MODE_LIVE

    var uId :String? = null
    var accessToken :String? = null

    private var isUser = false

    init {
        viewModelScope.launch {
            dataStoreRepository.getBoolean(DataStoreKey.PREF_KEY_IS_LOGINED)?.let {
                isUser = it
                if (it) {
                    accessToken = dataStoreRepository.getString(DataStoreKey.PREF_KEY_ACCESS_TOKEN)
                    uId = dataStoreRepository.getString(DataStoreKey.PREF_KEY_UID)
                }
            }

            appMode = ModeChanger.getInstance(dataStoreRepository).getMode()
        }
    }

    fun isUser() = isUser


}