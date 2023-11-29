package com.example.myutil.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthCodeDto(
    @field:SerializedName("state") val state: String,
    @field:SerializedName("authCode") val authCode: String
)
