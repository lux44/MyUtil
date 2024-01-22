package com.example.myutil.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PopupBannerDto(
    @field:SerializedName("accessType") val accessType: Int?, //링크접근권한 0:토큰 불필요 1:토큰 필수
    @field:SerializedName("applink") val applink: String?,
    @field:SerializedName("bannerId") val bannerId: Int?,
    @field:SerializedName("bannerImage") val bannerImage: String?,
    @field:SerializedName("displayEndDate") val displayEndDate: String?,
    @field:SerializedName("displayStartDate") val displayStartDate: String?,
    @field:SerializedName("link") val link: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("showType") val showType: Int?, //0 전체, 1 국내, 2 외국
) : java.io.Serializable