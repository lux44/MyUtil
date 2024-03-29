package com.example.myutil.data.remote.model

import com.google.gson.annotations.SerializedName

data class ConfigResponse(
    @field:SerializedName("serviceType") val serviceType: String?,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("deviceType") val deviceType: String?,
    @field:SerializedName("integUid") val integUid: String?,
    @field:SerializedName("enable") val enable: Int?,
    @field:SerializedName("endDate") val endDate: String?,
    @field:SerializedName("messageEng") val messageEng: String?,
    @field:SerializedName("messageKr") val messageKr: String?,
    @field:SerializedName("startDate") val startDate: String?,
    @field:SerializedName("apiUrl") val apiUrl: String?,
    @field:SerializedName("imageUrl") val imageUrl: String?,
    @field:SerializedName("transUrl") val transUrl: String?,
    @field:SerializedName("webUrl") val webUrl: String?,
    @field:SerializedName("adUrl") val adUrl: String?,
    @field:SerializedName("payUrl") val payUrl: String?,
    @field:SerializedName("currentVersion") val currentVersion: String?,
    @field:SerializedName("forceUpdate") val forceUpdate: Int?,
    @field:SerializedName("updateEnable") val updateEnable: Int?,
    @field:SerializedName("popupAppLink") val popupAppLink: String?,
    @field:SerializedName("popupImgKo") val popupImgKo: String?,
    @field:SerializedName("popupImgEn") val popupImgEn: String?,
    @field:SerializedName("popupStartDate") val popupStartDate: String?,
    @field:SerializedName("popupEndDate") val popupEndDate: String?,
    @field:SerializedName("popupEnable") val popupEnable: Int?,
)
