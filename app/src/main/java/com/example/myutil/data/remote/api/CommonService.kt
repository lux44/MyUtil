package com.example.myutil.data.remote.api

import com.example.myutil.data.remote.dto.PopupBannerDto
import com.example.myutil.data.remote.dto.UserInfoResponse
import com.example.myutil.data.remote.model.ConfigResponse
import com.example.myutil.data.remote.model.Country
import com.example.myutil.data.remote.model.CountryResponse
import com.example.myutil.data.remote.model.Language
import com.example.myutil.utils.ConstVariables.Config.Companion.KEY_DEVICE_TYPE
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CommonService {
    @GET("/country/select/all")
    suspend fun getCountryAll(
    ):List<Country>

    @GET("/country/select/iso2")
    suspend fun getSelectCountry(
        @Query("iso2") iso2: String
    ): CountryResponse

    @GET("/lang/all")
    suspend fun getSupportLanguageAll(
    ):List<Language>


    @GET("/mconfig/{serviceType}")
    suspend fun getConfig(
        @Path(value = "serviceType", encoded = true) serviceType:String,
        @Query(KEY_DEVICE_TYPE) deviceType:String
    ): ConfigResponse

    @POST("/user/info")
    suspend fun getUserInfo(
        @Header("access_token") authToken: String,
    ): UserInfoResponse

    @POST("/push/install")
    suspend fun setPushToken(
        @Header("access_token") authToken: String,
        @Body body: HashMap<String, Any>
    )

    @GET("/banner/front")
    suspend fun getFrontPopup(
    ): List<PopupBannerDto>
}