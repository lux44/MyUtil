package com.example.myutil.utils

import com.example.myutil.repositories.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ModeChanger @Inject constructor() {
    var dataStoreRepository: DataStoreRepository? = null

    companion object {
        const val KEY_MODE = "run_mode"
        const val MODE_DEBUG = 0
        const val MODE_LIVE = 1

        private var instance: ModeChanger? = null

        fun getInstance(dataStoreRepository: DataStoreRepository): ModeChanger {
            return instance ?: synchronized(this) {
                instance ?: ModeChanger().also {
                    GlobalScope.launch(Dispatchers.IO) {
                        it.dataStoreRepository = dataStoreRepository
                        it.mode = dataStoreRepository.getInt(KEY_MODE) ?: MODE_LIVE
                        instance = it
                    }
                }
            }
        }
    }

    private var mode: Int? = MODE_LIVE

    fun getMode(): Int {
        return mode ?: MODE_LIVE
    }

    suspend fun setMode(mode: Int) {
        dataStoreRepository?.putInt(KEY_MODE, mode)
        this.mode = mode
    }
}