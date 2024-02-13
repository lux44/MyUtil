package com.example.myutil.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myutil.repositories.DataStoreKey
import com.example.myutil.repositories.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository): ViewModel() {
    suspend fun getLanguageCode():String?{
        return dataStoreRepository.getString(DataStoreKey.PREF_KEY_LANGUAGE_CODE)
    }

    fun setLanguageCode(languageCode: String){
        viewModelScope.launch{
            dataStoreRepository.putString(DataStoreKey.PREF_KEY_LANGUAGE_CODE,languageCode)
        }
    }
}