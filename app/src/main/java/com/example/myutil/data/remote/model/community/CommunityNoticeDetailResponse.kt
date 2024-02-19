package com.example.myutil.data.remote.model.community

import com.example.myutil.data.remote.model.OpenGraph
import com.google.gson.annotations.SerializedName

data class CommunityNoticeDetailResponse (
    @SerializedName("notice")val notice : CommunityNoticeData,
    @SerializedName("attachList")val attachList : List<DetailAttachList>?,
    @SerializedName("openGraphList")val openGraphItems : List<OpenGraph>?
)

data class CommunityNoticeData(
    @SerializedName("postId")val postId : Int,
    @SerializedName("title")var title : String?,
    @SerializedName("content")var content : String?,
    @SerializedName("topYn")val topYn : Boolean?,
    @SerializedName("userNick")val userNick : String?,
    @SerializedName("userPhoto")val userPhoto : String?,
    @SerializedName("createDate")val createDate : String?,
    @SerializedName("translateYn")var translateYn : Boolean?,

)

