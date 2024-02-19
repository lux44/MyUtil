package com.example.myutil.data.remote.model

import com.google.gson.annotations.SerializedName

data class NewAlimListDto(
    @field:SerializedName("alimResList") val alimResList: List<NewAlimListResponse>,
    @field:SerializedName("count") val count: Int
)
{
    data class NewAlimListResponse(
        @field:SerializedName("alimId") val alimId: Int,
        @field:SerializedName("createDate") val createDate: String,
        @field:SerializedName("image") val image: String,
        @field:SerializedName("link") val link: String,
        @field:SerializedName("text01") val text01: String,
        @field:SerializedName("text02") val text02: String,
        @field:SerializedName("text03") val text03: String
    )
}