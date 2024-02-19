package com.example.myutil.data.remote.model

import com.example.myutil.data.remote.model.post.BoardPostData
import com.example.myutil.data.remote.model.post.ClubPostData
import com.google.gson.annotations.SerializedName

data class MainItemListResponse(
    @SerializedName("mainPostDtoList")val mainPostDtoList : List<PostListItem>,
    @SerializedName("listSize")val listSize : Int,
    @SerializedName("nextId") val nextId :String
)

data class PostListItem(
    @SerializedName("type") val type :String,
    @SerializedName("comPost") val communityPost : BoardPostData?,
    @SerializedName("clubPost") val clubPost : ClubPostData?,
)

data class ClubPostListResponse(
    @SerializedName("postList")val postList : List<ClubPostData>,
    @SerializedName("listSize")val listSize : Int,
    @SerializedName("nextId") val nextId :String
)

data class CommunityPostListResponse(
    @SerializedName("post")val postList : List<BoardPostData>,
    @SerializedName("listSize")val listSize : Int,
    @SerializedName("nextId") val nextId :String
)
