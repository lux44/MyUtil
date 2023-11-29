package com.example.myutil.repositories

import com.example.myutil.data.remote.BaseNetRepo
import com.example.myutil.data.remote.ResultWrapper
import com.example.myutil.data.remote.api.AuthService
import com.example.myutil.data.remote.dto.AccessTokenDto
import com.example.myutil.di.NetworkModule
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AuthRepository @Inject constructor(
    @NetworkModule.AuthServer private val service: AuthService
): BaseNetRepo() {
    suspend fun getAccessToken(
        values: HashMap<String, String>
    ): ResultWrapper<AccessTokenDto> = safeApiCall(Dispatchers.IO) {
        service.getAccessToken(values)
    }
}