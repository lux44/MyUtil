package com.example.myutil.data.remote.model.post

import androidx.room.ColumnInfo
import com.example.myutil.data.remote.model.OpenGraph
import com.example.myutil.data.remote.model.community.ClubPostDetailData
import com.example.myutil.data.remote.model.ftclub.Like
import com.example.myutil.utils.ConstVariables
import com.google.gson.annotations.SerializedName

data class ClubPostData (
    @ColumnInfo(name = "integUid")
    @SerializedName("integUid")val integUid : String?,
    @SerializedName("postId")val postId : Int,
    @SerializedName("clubId")val clubId : String,
    @SerializedName("memberId")val memberId : Int,
    @SerializedName("categoryId") var categoryId : String?,
    @SerializedName("parentCategoryId")val parentCategoryId : String?,
    @SerializedName("categoryCode") var categoryCode : String,
    @SerializedName("subject") var subject : String?,
    @SerializedName("content") var content : String?,
    @SerializedName("langCode")val langCode : String?,
    @SerializedName("createDate")val createDate : String,
    @SerializedName("attachList") var attachList : List<ClubPostAttachList>?,
    @SerializedName("hashtagList") var hashtagList : List<String>?,
    @SerializedName("replyCount") var replyCount : Int,
    @SerializedName("nickname")val nickname : String?,
    @SerializedName("memberLevel")val memberLevel : Int,
    @SerializedName("url")val url : String?,
    @SerializedName("categoryName1")val categoryName1 : String?,
    @SerializedName("categoryName2")val categoryName2 : String?,
    @SerializedName("status") var status : Int,
    @SerializedName("blockType") var blockType : Int,
    @SerializedName("profileImg")val profileImg : String?,
    @SerializedName("clubProfileImg")val clubProfileImg : String?,
    @SerializedName("like") var like : ClubLike?,
    @SerializedName("boardType") var boardType : Int?,
    @SerializedName("clubName")val clubName : String?,
    @SerializedName("firstImage")val firstImage : String?,
    @SerializedName("attachType") var attachType : Int?,
    @SerializedName("deleteType") var deleteType : Int?,
    @SerializedName("link")val link : String?,
    @SerializedName("linkType")val linkType : Int?,
    @SerializedName("openGraphList") var openGraphList : List<OpenGraph>?,
    @SerializedName("topYn") var topYn : Boolean,
    @SerializedName("reportYn")val reportYn : Boolean,
    @SerializedName("clubMemberYn")val clubMemberYn : Boolean?,
    @SerializedName("myPostYn")val myPostYn : Boolean,
    @SerializedName("clubMemberBlockYn")val clubMemberBlockYn : Boolean?,
    @SerializedName("memberStatus") val memberStatus : Int?,
    @SerializedName("honor")val honor : HonorItem?,
//    translate
    var translateState :Int?,
    var translateSubject : String?,
    var translateContent : String?,
) : PostListData(){
    fun toClubPostDetailData(): DetailPostItem =
        DetailPostItem.ClubPostDetail(
            ConstVariables.TYPE_CLUB,
            ClubPostDetailData(
                postId = postId,
                clubId = clubId,
                memberId = memberId ?: 0,
                parentCategoryId = "",
                categoryCode = categoryCode,
                subject = subject ?: "",
                content = content ?: "",
                langCode = langCode.orEmpty(),
                createDate = createDate,
                attachList = attachList?.map { it.toClubPostAttachList() },
                hashtagList = hashtagList,
                openGraphList = null,
                replyCount = replyCount,
                nickname = nickname,
                clubName = clubName ?: "",
                boardType = boardType,
                memberLevel = memberLevel ?: 1,
                url = url?:"",
                categoryName1 = categoryName1,
                categoryName2 = categoryName2,
                status = status,
                blockType = blockType?.toInt() ?: 0,
                profileImg = profileImg,
                attachType = attachType,
                deleteType = deleteType ?: 0,
                like = like?.toDetailLike(),
                memberUrl = "",
                reportYn = null,
                clubMemberYn = false,
                myPostYn = false,
                topYn = false,
                myHonorYn = null,
                translateYn = null,
                isBookmarkYn = null,
                integUid = null,
                categoryId = "",
                honor = null,
                honorCount = null,
                memberStatus = memberStatus
            )
        )
}

data class ClubPostAttachList(
    @SerializedName("attach")val attach : String,
    @SerializedName("attachType")val attachType : Int,
    @SerializedName("attachId")val attachId : Int
) {
    fun toClubPostAttachList()=ClubPostAttachList(
        attach = attach,
        attachType = attachType,
        0
    )
}

data class HonorItem(
    @SerializedName("honorCount")val honorCount : Int,
    @SerializedName("honorYn")val honorYn : Boolean,
)

data class ClubLike(
    @SerializedName("likeYn") var likeYn :Boolean?,
    @SerializedName("likeCount") var likeCount :Int,
    @SerializedName("dislikeCount") var dislikeCount :Int,
){
    fun toDetailLike()= Like(likeYn, likeCount, dislikeCount) //todo :통일 시키기
}