package com.example.myutil.data.remote.dto

import com.example.myutil.data.remote.model.post.ClubPostData
import com.google.gson.annotations.SerializedName


data class ClubPagePostResponse (
    @SerializedName("postList") val postList : List<ClubPostData>,
    @SerializedName("listSize") val listSize : Int,
    @SerializedName("nextId") val nextId : String?,
)