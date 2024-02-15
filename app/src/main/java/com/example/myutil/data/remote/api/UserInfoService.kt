package com.example.myutil.data.remote.api

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserInfoService {

    @POST("my/referral/use")
    suspend fun registerReferralCode(
        @Header("access_token") accessToken: String,
        @Body referral: HashMap<String, String>
    )
}