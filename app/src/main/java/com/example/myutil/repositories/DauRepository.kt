package com.example.myutil.repositories

import com.example.myutil.data.remote.api.DauService
import javax.inject.Inject
import com.example.myutil.di.NetworkModule

class DauRepository @Inject constructor(@NetworkModule.ApiServer private val dauService: DauService){
    suspend fun updateDau(accessToken:String){
        dauService.updateDauCount(accessToken)
    }
}