package com.example.myutil.data.remote.model.ftclub

import android.os.Parcelable
import com.example.myutil.data.remote.model.community.ClubPostDetailData
import com.example.myutil.data.remote.model.post.ClubLike
import com.example.myutil.data.remote.model.post.ClubPostAttachList
import com.example.myutil.data.remote.model.post.DetailPostItem
import com.example.myutil.utils.ConstVariables.Notification.TYPE_CLUB
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class FantooClubPost(
    @SerializedName("attachList") val attachList: List<FantooClubPostAttach>?,
    @SerializedName("attachType") val attachType: Int?,
    @SerializedName("blockType") val blockType: String?,
    @SerializedName("boardType") val boardType: Int?,
    @SerializedName("categoryCode") val categoryCode: String,
    @SerializedName("categoryName1") val categoryName1: String?,
    @SerializedName("categoryName2") val categoryName2: String,
    @SerializedName("clubId") val clubId: String,
    @SerializedName("clubName") val clubName: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("createDate") val createDate: String,
    @SerializedName("deleteType") val deleteType: Int?,
    @SerializedName("firstImage") val firstImage: String?,
    @SerializedName("hashtagList") val hashtagList: List<String>?,
    @SerializedName("honor") val honor: Honor?,
    @SerializedName("integUid") val integUid: String?,
    @SerializedName("langCode") val langCode: String?,
    @SerializedName("like") var like: Like?,
    @SerializedName("link") val link: String?,
    @SerializedName("memberId") val memberId: Int?,
    @SerializedName("memberLevel") val memberLevel: Int?,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("postId") val postId: Int,
    @SerializedName("profileImg") val profileImg: String?,
    @SerializedName("replyCount") val replyCount: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("subject") val subject: String?,
    @SerializedName("url") val url: String,
    @SerializedName("memberStatus") val memberStatus :Int?,
    var translatedTitle: String? = null,
    var translatedBody: String? = null,
    var isTaboola: Boolean = false,
    var fromRoute: String? = null
) : Parcelable{
    fun toClubPostDetailData(): DetailPostItem =
        DetailPostItem.ClubPostDetail(
            TYPE_CLUB,
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
                url = url,
                categoryName1 = categoryName1,
                categoryName2 = categoryName2,
                status = status,
                blockType = blockType?.toInt() ?: 0,
                profileImg = profileImg,
                attachType = attachType,
                deleteType = deleteType ?: 0,
                like = like,
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

@Parcelize
@Serializable
data class FantooClubPostAttach(
    @SerializedName("attach") var attach: String,
    @SerializedName("attachType") var attachType: Int
) : Parcelable{
    fun toClubPostAttachList()= ClubPostAttachList(
        attach = attach,
        attachType = attachType,
        0
    )
}

@Parcelize
@Serializable
data class Honor(
    @SerializedName("honorCount") val honorCount: Int?,
    @SerializedName("honorYn") val honorYn: Boolean?,
) : Parcelable

@Parcelize
@Serializable
data class Like(
    @SerializedName("likeYn") var likeYn: Boolean?,
    @SerializedName("likeCount") var likeCount: Int,
    @SerializedName("dislikeCount") var dislikeCount: Int,
) : Parcelable{
    fun toClubLike()= ClubLike(
        likeYn, likeCount, dislikeCount
    )
}