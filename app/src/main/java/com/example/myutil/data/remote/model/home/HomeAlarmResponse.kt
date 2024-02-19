package com.example.myutil.data.remote.model.home

import com.google.gson.annotations.SerializedName

data class HomeAlarmResponse(
    @SerializedName("alimList") val alimList: List<HomeAlarmData>,
    @SerializedName("listSize") val listSize: Int,
    @SerializedName("nextId") val nextId: Int
)

data class HomeAlarmData(
    @SerializedName("alimId") var alimId: Int,
    @SerializedName("createDate") var createDate: String,
    @SerializedName("link") var link: String,
    @SerializedName("text01") var text01: String?,
    @SerializedName("text02") var text02: String?,
    @SerializedName("text03") var text03: String?,
    @SerializedName("alarmThumbnail") var alarmThumbnail: String?,
    @SerializedName("alarmSender") var alarmSender: String,
    @SerializedName("isRead") var isRead : Boolean?,
    @SerializedName("status") var status: Int
)