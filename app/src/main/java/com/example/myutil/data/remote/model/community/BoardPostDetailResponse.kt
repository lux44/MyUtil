package com.example.myutil.data.remote.model.community

import com.example.myutil.data.remote.model.OpenGraph
import com.example.myutil.data.remote.model.ftclub.Like
import com.example.myutil.data.remote.model.post.ClubPostAttachList
import com.example.myutil.data.remote.model.post.HonorItem
import com.google.gson.annotations.SerializedName

sealed class PostDetailData

data class BoardPostDetailResponse(
    @SerializedName("post") val post : BoardPostDetailData,
    @SerializedName("openGraphList") val openGraphList : List<OpenGraph>?,
    @SerializedName("attachList") var attachList: List<DetailAttachList>,
    @SerializedName("hashtagList") var hashtagList: List<BoardPostDetailHashTag>
)

data class BoardPostDetailData(
    @SerializedName("postId") val postId :Int,
    @SerializedName("code") val code :String,
    @SerializedName("subCode") val subCode : String,
    @SerializedName("title") var title:String?,
    @SerializedName("content") var content : String?,
    @SerializedName("integUid") val integUid : String,
    @SerializedName("langCode") val langCode : String,
    @SerializedName("activeStatus") var activeStatus :Int,
    @SerializedName("likeCnt") var likeCnt :Int?,
    @SerializedName("dislikeCnt") var dislikeCnt :Int?,
    @SerializedName("honorCnt") var honorCnt :Int?,
    @SerializedName("replyCnt") var replyCnt :Int?,
    @SerializedName("anonymYn") var anonymYn :Boolean,
    @SerializedName("likeYn") var myLikeYn : Boolean?,
    @SerializedName("dislikeYn") var myDisLikeYn : Boolean?,
    @SerializedName("userBlockYn") var userBlockYn : Boolean?,
    @SerializedName("pieceBlockYn") var pieceBlockYn : Boolean?,
    @SerializedName("reportYn") var reportYn : Boolean?,
    @SerializedName("userNick") val userNick :String?,
    @SerializedName("userPhoto") val userPhoto : String?,
    @SerializedName("userStatus") var userStatus : Int?,
    @SerializedName("createDate") val createDate :String?,
    @SerializedName("bookmarkYn") var bookmarkYn : Boolean,
//    아래 데이터는 임의 추가된 데이터임!
    @SerializedName("myHonorYn") var myHonorYn : Boolean?,
    @SerializedName("translateYn") var translateYn : Boolean?,
) :PostDetailData()

data class ClubPostDetailData(
    @SerializedName("integUid") val integUid :String?,
    @SerializedName("postId") val postId :Int,
    @SerializedName("clubId") val clubId :String,
    @SerializedName("memberId") val memberId :Int,
    @SerializedName("categoryId") val categoryId :String,
    @SerializedName("parentCategoryId") val parentCategoryId :String,
    @SerializedName("categoryCode") val categoryCode :String,
    @SerializedName("subject") var subject :String,
    @SerializedName("content") var content :String,
    @SerializedName("langCode") val langCode :String,
    @SerializedName("createDate") val createDate :String,
    @SerializedName("attachList") val attachList : List<ClubPostAttachList>?,
    @SerializedName("hashtagList") val hashtagList : List<String>?,
    @SerializedName("openGraphList") val openGraphList : List<OpenGraph>?,
    @SerializedName("replyCount") var replyCount : Int,
    @SerializedName("honorCount") val honorCount : Int?,
    @SerializedName("nickname") val nickname : String?,
    @SerializedName("clubName") val clubName : String,
    @SerializedName("boardType") val boardType : Int?,
    @SerializedName("memberLevel") val memberLevel : Int,
    @SerializedName("url") val url : String,
    @SerializedName("memberUrl") val memberUrl : String,
    @SerializedName("categoryName1") val categoryName1 : String?,
    @SerializedName("categoryName2") val categoryName2 : String?,
    @SerializedName("status") val status : Int,
    @SerializedName("blockType") var blockType : Int,
    @SerializedName("reportYn") val reportYn : Boolean?,
    @SerializedName("profileImg") val profileImg : String?,
    @SerializedName("honor") val honor : HonorItem?,
    @SerializedName("attachType") val attachType : Int?,
    @SerializedName("deleteType") val deleteType :Int,
    @SerializedName("clubMemberYn") val clubMemberYn :Boolean,
    @SerializedName("myPostYn") val myPostYn :Boolean,
    @SerializedName("topYn") val topYn :Boolean?,
    @SerializedName("like") var like: Like?,
    //    아래 데이터는 임의 추가된 데이터임!
    @SerializedName("myHonorYn") var myHonorYn : Boolean?,
    @SerializedName("translateYn") var translateYn : Boolean?,
    @SerializedName("isBookmarkYn") var isBookmarkYn : Boolean?,
    @SerializedName("memberStatus") val memberStatus :Int?
) : PostDetailData()

data class DetailAttachList(
    @SerializedName("attachType") val archiveType :String,
    @SerializedName("id") val id : String?
)

data class BoardPostDetailHashTag(
    @SerializedName("tag")val tagText :String
)