package com.example.myutil.data.remote.model.home

import com.example.myutil.data.remote.model.OpenGraph
import com.example.myutil.ui.common.viewgroup.TranslateState.TRANSLATE_STATE_NONE
import com.google.gson.annotations.SerializedName

/**
 *  mainType            {string}            : 게시글 타입 구분자. com : 커뮤니티, club : 클럽, fantootv : 팬투티비, times : 한류타임즈, fansing: 팬싱, minute : 미닛, banner : 배너, taboola : 광고
 *  idx                 {integer}           : 게시글 Id
 *  integUid            {string}            : 게시글 작성자 integUid
 *  status              {Int}               : 게시글 상태
 *  clubId              {string}            : 클럽 ID
 *  clubMemberId        {string}            : 클럽 멤버 ID
 *  clubCategoryId      {string}            : 클럽 카테고리 ID
 *  parentCategoryId    {string}            : 클럽 부모 카테고리 ID
 *  subject             {string}            : 게시글 제목
 *  content             {string}            : 게시글 내용
 *  langCode            {string}            : 언어코드
 *  firstImage          {string}            : 게시글 미리보기 이미지
 *  createDate          {string}            : (yyyy-MM-dd'T'hh:mm:ss) 게시글 생성일
 *  userId              {integer}           : 회원 Id
 *  userNick            {string}            : 회원 닉네임
 *  userStatus          {integer}           : 회원 상태값 0 : 정상, 1 : 신고, 2: 탈퇴
 *  clubName            {string}            : 클럽 이름
 *  userPhoto           {string}            : 회원 프로필 이미지
 *  likeCnt             {integer}           : 좋아요 수
 *  replyCnt            {integer}           : 댓글 수
 *  likeYn              {boolean}           : 좋아요 여부
 *  postBlockYn         {boolean}           : 게시글 차단 여부
 *  memberBlockYn       {boolean}           : 회원 차단 여부
 *  favoriteYn          {boolean}           : 북마크 여부
 *  reportYn            {boolean}           : 게시글 신고 여부
 *  openGraphDtoList    {List<OpenGraph>}   : openGraphList
 *  attachs             {List}              : attachs
 *  bannerId            {Int}               :
 *  title               {String}            :
 *  division            {String}            :
 *  bannerImage         {String}            :
 *  displayStartDate    {String}            :
 *  displayEndDate      {String}            :
 *  link                {String}            :
 *  sort                {Int}               :
 *  accessType          {Int}               :
 *  createBy            {String}            :
 *  updateBy            {String}            :
 *  updateDate          {String}            :
 *  deleteBy            {String}            :
 *  deleteDate          {String}            :
 *  applink             {String}            :
 *  showTopbar          {Int}               :
 *  showEventYn         {Boolean}           :
 */

data class HomeNewestData(
    @SerializedName("mainType") val mainType : String,
    @SerializedName("idx") val idx : Int?,
    @SerializedName("integUid") val integUid : String?,
    @SerializedName("status") val status : Int?,
    @SerializedName("clubId") val clubId : String?,
    @SerializedName("clubMemberId") val clubMemberId : String?,
    @SerializedName("clubCategoryId") val clubCategoryId : String?,
    @SerializedName("clubCategoryCode") val clubCategoryCode : String?,
    @SerializedName("parentCategoryId") val parentCategoryId : String?,
    @SerializedName("subject") val subject : String?,
    @SerializedName("content") val content : String?,
    @SerializedName("langCode") val langCode : String?,
    @SerializedName("firstImage") val firstImage : String?,
    @SerializedName("createDate") val createDate : String?,
    @SerializedName("userId") val userId : Int?,
    @SerializedName("userNick") val userNick : String?,
    @SerializedName("userStatus") val userStatus : Int?,
    @SerializedName("clubName") val clubName : String?,
    @SerializedName("userPhoto") val userPhoto : String?,
    @SerializedName("clubProfileImg") val clubProfileImg : String?,
    @SerializedName("likeCnt") var likeCnt : Int?,
    @SerializedName("replyCnt") var replyCnt : Int?,
    @SerializedName("likeYn") var likeYn : Boolean?,
    @SerializedName("postBlockYn") var postBlockYn : Boolean?,
    @SerializedName("memberBlockYn") var memberBlockYn : Boolean?,
    @SerializedName("favoriteYn") val favoriteYn : Boolean?,
    @SerializedName("reportYn") val reportYn : Boolean?,
    @SerializedName("openGraphDtoList") val openGraphDtoList : List<OpenGraph>?,
    @SerializedName("attachs") val attachs : List<PostAttachList>?,
    @SerializedName("bannerId") val bannerId : Int?,
    @SerializedName("title") val title : String?,
    @SerializedName("division") val division : String?,
    @SerializedName("bannerImage") val bannerImage : String?,
    @SerializedName("displayStartDate") val displayStartDate : String?,
    @SerializedName("displayEndDate") val displayEndDate : String?,
    @SerializedName("link") val link : String?,
    @SerializedName("sort") val sort : Int?,
    @SerializedName("accessType") val accessType : Int?,
    @SerializedName("createBy") val createBy : String?,
    @SerializedName("updateBy") val updateBy : String?,
    @SerializedName("updateDate") val updateDate : String?,
    @SerializedName("deleteBy") val deleteBy : String?,
    @SerializedName("deleteDate") val deleteDate : String?,
    @SerializedName("applink") val applink : String?,
    @SerializedName("showTopbar") val showTopbar : Int?,
    @SerializedName("showEventYn") val showEventYn : Boolean?,
    //---------------
    var translateState : Int = TRANSLATE_STATE_NONE,
    var translateTitle :String?,
    var translateContent :String?,
    var taboolaPos : Int,
)

data class PostAttachList(
    @SerializedName("attach")val attach : String,
    @SerializedName("attachType")val attachType : Int,
)

data class HomeNewestDataResponse(
    @SerializedName("newestDtoList") val newestDtoList : List<HomeNewestData>,
    @SerializedName("page") val page :Int,
)