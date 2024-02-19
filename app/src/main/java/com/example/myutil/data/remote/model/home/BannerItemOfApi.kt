package com.example.myutil.data.remote.model.home

import com.google.gson.annotations.SerializedName

data class BannerItemOfApi (
    @SerializedName("bannerId") val bannerId :Int,
    @SerializedName("bannerImage") val bannerImage :String?,
    @SerializedName("link") val link :String?,
    @SerializedName("applink") val applink :String?,
    @SerializedName("accessType") val accessType :Int?,
)