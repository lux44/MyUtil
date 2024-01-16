package com.example.myutil.data.remote.api

import com.example.myutil.utils.ConstVariables
import retrofit2.http.Header
import retrofit2.http.POST

interface DauService {
    @POST("analysis/dau")
    suspend fun updateDauCount(
        @Header(ConstVariables.KEY_ACCESS_TOKEN) accessToken :String
    )
}