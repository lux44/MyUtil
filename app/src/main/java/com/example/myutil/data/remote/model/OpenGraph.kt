package com.example.myutil.data.remote.model

import com.google.gson.annotations.SerializedName

data class OpenGraph(
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("domain") val domain: String?,
    @field:SerializedName("image") val image: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("url") val url: String?
)