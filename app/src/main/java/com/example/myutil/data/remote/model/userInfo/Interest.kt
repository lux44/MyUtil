package com.example.myutil.data.remote.model.userInfo

import com.google.gson.annotations.SerializedName

data class Interest(
    @field:SerializedName("code") val code: String
)

data class InterestList(
    @field:SerializedName("interestList") val interestList: List<Interest>
)