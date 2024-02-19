package com.example.myutil.data.remote.api

import com.example.myutil.data.remote.dto.ClubAllInfoListResponse
import com.example.myutil.data.remote.dto.ClubPagePostResponse
import com.example.myutil.data.remote.model.ClubPostListResponse
import com.example.myutil.data.remote.model.CommunityPostListResponse
import com.example.myutil.data.remote.model.MainItemListResponse
import com.example.myutil.data.remote.model.NewAlimListDto
import com.example.myutil.data.remote.model.RecommendationClubResponse
import com.example.myutil.data.remote.model.home.BannerItemOfApi
import com.example.myutil.data.remote.model.home.HomeAlarmResponse
import com.example.myutil.data.remote.model.home.HomeNewestDataResponse
import com.example.myutil.data.remote.model.home.TrendingListDto
import com.example.myutil.data.remote.model.post.BoardPostDataObj
import com.example.myutil.data.remote.model.post.ClubPostData
import com.example.myutil.utils.ConstVariables
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {
    @GET("/banner")
    suspend fun fetchHomeBanner() : List<BannerItemOfApi>

    @GET("/club/main/popular/user/club/top10")
    suspend fun getSuitRecommendClub(
        @Query(ConstVariables.KEY_UID) uid: String?,
    ) : RecommendationClubResponse

    //인기 클럽 TOP 10
    @GET("/club/main/popular/club/top10")
    suspend fun getHotRecommendClub(
        @Query(ConstVariables.KEY_UID) uid: String?,
    ) : RecommendationClubResponse

    //인기 클럽 TOP 10 (비로그인)
    @GET("/club/main/guest/popular/club/top10")
    suspend fun getGuestHotRecommendClub(
    ) : RecommendationClubResponse

    @GET("/main/popular/operation/club")
    suspend fun getFantooContentsOfPopular(
        @Header(ConstVariables.KEY_ACCESS_TOKEN) accessToken :String
    ) :List<ClubPostData>
    @GET("/main/guest/popular/operation/club")
    suspend fun getFantooContentsOfPopularForGuest() :List<ClubPostData>

    @GET("/main/home")
    suspend fun getHomeMain(
        @Header(ConstVariables.KEY_ACCESS_TOKEN) accessToken:String,
        @Query(ConstVariables.KEY_UID) uid: String?,
        @Query("nextId") nextId : String?,
        @Query("size") size :String
    ) : MainItemListResponse

    @GET("main/guest/newest")
    suspend fun fetchNewestDataForGuest(
        @Query("page") page :Int,
    ) : HomeNewestDataResponse

    @GET("main/newest")
    suspend fun fetchNewestData(
        @Header(ConstVariables.KEY_ACCESS_TOKEN) accessToken: String,
        @Query("page") page :Int,
    ) : HomeNewestDataResponse

    @GET("/main/popular/trending/search")
    suspend fun getPopularTrendingList(
        @Query("size") size :Int
    ) : TrendingListDto

    @GET("/main/popular")
    suspend fun getPopular(
        @Header(ConstVariables.KEY_HEADER_LANG) langCode:String?,
        @Query(ConstVariables.KEY_UID) uid: String?,
        @Query("globalYn") globalYn: Boolean,
        @Query("nextId") nextId : String?,
        @Query("size") size :Int
    ) : MainItemListResponse
    @GET("/main/guest/popular")
    suspend fun getPopularForGuest(
        @Header(ConstVariables.KEY_HEADER_LANG) langCode:String?,
        @Query("globalYn") globalYn: Boolean,
        @Query("nextId") nextId : String?,
        @Query("size") size :Int
    ) : MainItemListResponse

    //HomeAlarm List Api
    @GET("/alim/user/message")
    suspend fun getAlarmList(
        @Header(ConstVariables.KEY_ACCESS_TOKEN) accessToken: String,
        @Query("nextId") nextId : String?,
        @Query("size") size :String
    ) : HomeAlarmResponse

    @GET("/main/popular/hashtag") //회원,비회원 공용
    suspend fun getTrendingPostList(
        @Query(ConstVariables.KEY_UID) uId :String?,
        @Query("hashtag") hashTag :String,
        @Query("nextId") nextId : String?,
        @Query("size") size :Int
    ):MainItemListResponse

    @GET("/club/{clubId}/board/hashtag/post")
    suspend fun getClubHashTagPostList(
        @Path("clubId") clubId: String?,
        @Query(ConstVariables.KEY_UID) uId :String?,
        @Query("hashtag") hashTag :String,
        @Query("nextId") nextId : String?,
        @Query("size") size :Int,
        @Query("sort") sort :String?
    ): ClubPostListResponse
    @GET("/club/{clubId}/guest/board/hashtag/post")
    suspend fun getClubGuestHashTagPostList(
        @Path("clubId") clubId: String?,
        @Query("hashtag") hashTag :String,
        @Query("nextId") nextId : String?,
        @Query("size") size :Int,
        @Query("sort") sort :String?
    ):ClubPostListResponse

    @GET("/community/hashtag/post")
    suspend fun getCommunityHashTagPostList(
        @Query(ConstVariables.KEY_UID) uId :String?,
        @Query("hashtag") hashTag :String,
        @Query("nextId") nextId : String?,
        @Query("size") size :Int,
    ): CommunityPostListResponse

    @GET("/community/guest/hashtag/post")
    suspend fun getGuestCommunityHashTagPostList(
        @Query("hashtag") hashTag :String,
        @Query("nextId") nextId : String?,
        @Query("size") size :Int,
    ):CommunityPostListResponse

    @GET("/alim/count")
    suspend fun getNewAlimList(
        @Header(ConstVariables.KEY_ACCESS_TOKEN) accessToken :String,
    ): NewAlimListDto

    @PATCH("/alim/read/{id}")
    suspend fun requestAlimRead(
        @Header(ConstVariables.KEY_ACCESS_TOKEN) accessToken :String,
        @Path("id") id: String?
    )
    @DELETE("/alim/delete/{id}")
    suspend fun requestDeleteAlim(
        @Header(ConstVariables.KEY_ACCESS_TOKEN) accessToken :String,
        @Path("id") id: String
    )


    //Main Search start
    @GET("/main/guest/popular/club/top3")
    suspend fun getGuestTopPopularClubList(
    ): ClubAllInfoListResponse

    @GET("/main/popular/club/top3")
    suspend fun getTopPopularClubList(
        @Header(ConstVariables.KEY_ACCESS_TOKEN) accessToken:String,
    ): ClubAllInfoListResponse

    @GET("/main/guest/community/post/search")
    suspend fun searchGuestCommunityPost(
        @Query("keyword") keyword : String,
        @Query("nextId") nextId : String?,
        @Query("size") size :String,
        @Query("sort") sort :String
    ): BoardPostDataObj

    @GET("/main/community/post/search")
    suspend fun searchCommunityPost(
        @Header(ConstVariables.KEY_ACCESS_TOKEN) accessToken:String,
        @Query("keyword") keyword : String,
        @Query("nextId") nextId : String?,
        @Query("size") size :String,
        @Query("sort") sort :String
    ): BoardPostDataObj

    @GET("/main/guest/club/search")
    suspend fun searchClub(
        @Query("keyword") keyword : String,
        @Query("nextId") nextId : String?,
        @Query("size") size :String,
        @Query("sort") sort :String
    ): ClubAllInfoListResponse

    @GET("/main/guest/club/post/search")
    suspend fun searchGuestClubPost(
        @Query("keyword") keyword : String,
        @Query("nextId") nextId : String?,
        @Query("size") size :String,
        @Query("sort") sort :String
    ): ClubPagePostResponse
    //Main Search end
}