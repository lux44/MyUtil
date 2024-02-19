package com.example.myutil.data.remote.dto

import android.os.Parcelable
import com.example.myutil.data.remote.ErrorData
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ClubAllInfoResponse(
    val code: String,
    val msg: String,
    val clubAllInfo: ClubAllInfoDto?,
    val errorData: ErrorData?
)

//for home search
data class ClubAllInfoListResponse(
    @field:SerializedName("clubList") val clubList: List<ClubAllInfoDto>,
    @field:SerializedName("listSize") val listSize: Int,
    @field:SerializedName("nextId") val nextId: Int?,
)

data class ClubAllInfoDto(
    @field:SerializedName("clubId") val clubId: Int,
    @field:SerializedName("clubName") val clubName: String,
    @field:SerializedName("introduction") val introduction: String?,
    @field:SerializedName("memberListOpenYn") val memberListOpenYn: Boolean?,
    @field:SerializedName("memberCountOpenYn") val memberCountOpenYn: Boolean,
    @field:SerializedName("openYn") val openYn: Boolean,
    @field:SerializedName("interestCategoryCode") val interestCategoryCode: String?,
    @field:SerializedName("codeName") val codeName: String?,
    @field:SerializedName("languageCode") val languageCode: String?,
    @field:SerializedName("langCode") val langCode: String?,
    @field:SerializedName("langName") val langName: String?,
    @field:SerializedName("activeCountryCode") val activeCountryCode: String?,
    @field:SerializedName("acCountryCode") val acCountryCode: String?,
    @field:SerializedName("acCountryName") val acCountryName: String?,
    @field:SerializedName("memberJoinAutoYn") val memberJoinAutoYn: Boolean,
    @field:SerializedName("createDate") val createDate: String,
    @field:SerializedName("clubProfileImg") val clubProfileImg: String?,
    @field:SerializedName("bgImg") val bgImg: String?,
    @field:SerializedName("memberCount") val memberCount: Int,
    @field:SerializedName("postCount") val postCount: Int,
    @field:SerializedName("joinMemberCount") val joinMemberCount: Int,
    @field:SerializedName("hashtagList") val hashtagList: List<String>?,
    @field:SerializedName("clubMasterNickname") val clubMasterNickname: String,
    @field:SerializedName("clubMasterProfileImg")val clubMasterProfileImg : String?,
    @field:SerializedName("clubMasterYn")val clubMasterYn : Boolean?,
    @field:SerializedName("visitDate") val visitDate: String?,
    @field:SerializedName("joinStatus") val joinStatus: Int,
    //    임의 추가 데이터
    @SerializedName("favoriteYN")var favoriteYN : Boolean,
    @SerializedName("memberId")var memberId : Int?,
    @SerializedName("memberLevel")var memberLevel : Int=-1,
    @SerializedName("clubJoinState")var clubJoinState : Int=-1,
) {
    fun toClubAllInfoPacerable(): ClubAllInfoPacerable {
        return ClubAllInfoPacerable(
            clubId = clubId,
            clubName = clubName,
            introduction = introduction,
            memberCountOpenYn = memberCountOpenYn,
            memberListOpenYn = memberListOpenYn,
            openYn = openYn,
            interestCategoryCode = interestCategoryCode,
            languageCode = languageCode,
            activeCountryCode = activeCountryCode,
            memberJoinAutoYn = memberJoinAutoYn,
            createDate = createDate,
            visitDate = visitDate,
            clubProfileImg = clubProfileImg,
            bgImg = bgImg,
            memberCount = memberCount,
            clubMasterNickname = clubMasterNickname,
            hashtagList = hashtagList,
            joinMemberCount = joinMemberCount,
            joinStatus = joinStatus,
            postCount = postCount,
            clubMasterProfileImg = clubMasterProfileImg,
            clubMasterYn = clubMasterYn
        )
    }
}

@Parcelize
data class ClubAllInfoPacerable(
    val clubId: Int,
    val clubName: String,
    val introduction: String?,
    val memberListOpenYn: Boolean?,
    val memberCountOpenYn: Boolean,
    val openYn: Boolean,
    val interestCategoryCode: String?,
    val languageCode: String?,
    val activeCountryCode: String?,
    val memberJoinAutoYn: Boolean,
    val createDate: String,
    val clubProfileImg: String?,
    val bgImg: String?,
    val memberCount: Int,
    val postCount: Int,
    val joinMemberCount: Int,
    val hashtagList: List<String>?,
    val clubMasterNickname: String,
    val clubMasterProfileImg: String?,
    val clubMasterYn: Boolean?,
    val visitDate: String?,
    val joinStatus: Int,
) : Parcelable

