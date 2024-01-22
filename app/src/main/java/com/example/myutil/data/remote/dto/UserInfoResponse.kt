package com.example.myutil.data.remote.dto

import com.example.myutil.data.remote.model.userInfo.Interest
import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @field:SerializedName("adAgreeYn") var adAgreeYn: Boolean,
    @field:SerializedName("birthDay") var birthDay: String?,
    @field:SerializedName("createDate") val createDate: String?,
    @field:SerializedName("cellCode") val cellCode: String?,
    @field:SerializedName("cellNum") val cellNum: String?,
    @field:SerializedName("countryIsoTwo") var countryIsoTwo: String?,
    @field:SerializedName("deviceId") val deviceId: String?,
    @field:SerializedName("email") val email: String?,
    @field:SerializedName("fnsCoins") val fnsCoins: String?,
    @field:SerializedName("genderType") var genderType: String?,
    @field:SerializedName("integUid") val integUid: String?,
    @field:SerializedName("interestList") var interestList: List<Interest>?,
    @field:SerializedName("introduce") val introduce: String?,
    @field:SerializedName("langCode") val langCode: String?,
    @field:SerializedName("lastLoginDate") val lastLoginDate: String?,
    @field:SerializedName("loginId") val loginId: String?,
    @field:SerializedName("loginPwUpdate") val loginPwUpdate: String?,
    @field:SerializedName("loginType") val loginType: String,
    @field:SerializedName("pandroidToken") val pandroidToken: String?,
    @field:SerializedName("piosToken") val piosToken: String?,
    @field:SerializedName("professionalPublisher") val professionalPublisher: Boolean,
    @field:SerializedName("referralCode") val referralCode: String?,
    @field:SerializedName("sortStatus") val sortStatus: String?,
    @field:SerializedName("useReferralCode") val useReferralCode: String?,
    @field:SerializedName("userName") val userName: String?,
    @field:SerializedName("userNick") var userNick: String?,
    @field:SerializedName("userNickUpdate") val userNickUpdate: String?,
    @field:SerializedName("userPhoto") var userPhoto: String?,
)