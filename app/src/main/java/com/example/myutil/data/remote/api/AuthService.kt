package com.example.myutil.data.remote.api

import com.example.myutil.data.remote.dto.AccessTokenDto
import com.example.myutil.data.remote.dto.AuthCodeDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("oauth2.0/authorize")
    suspend fun getAuthCode(
        @Body values: HashMap<String, String>
    ): AuthCodeDto

    @POST("oauth2.0/token")
    suspend fun getAccessToken(
        @Body values: HashMap<String, String>
    ): AccessTokenDto
}