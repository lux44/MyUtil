package com.example.myutil

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.myutil.repositories.DataStoreRepository
import com.example.myutil.utils.ModeChanger
import com.example.myutil.utils.ReleaseTree
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import timber.log.Timber

@HiltAndroidApp
class UtilApp: Application() {

    override fun onCreate() {

        val entryPoint = EntryPointAccessors.fromApplication(this, UtilAppEntryPoint::class.java)
        ModeChanger.getInstance(dataStoreRepository = entryPoint.dataStoreRepository())

        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }else{
            Timber.plant(ReleaseTree())
        }
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface UtilAppEntryPoint {
        fun dataStoreRepository(): DataStoreRepository
    }
}