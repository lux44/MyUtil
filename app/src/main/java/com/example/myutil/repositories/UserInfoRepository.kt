package com.example.myutil.repositories

import com.example.myutil.data.local.dao.MyProfileDao
import com.example.myutil.data.remote.BaseNetRepo
import com.example.myutil.data.remote.api.UserInfoService
import com.example.myutil.di.NetworkModule
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import javax.inject.Inject

class UserInfoRepository @Inject constructor(
    @NetworkModule.ApiServer private val userInfoService: UserInfoService,
    private val myProfileDao: MyProfileDao
): BaseNetRepo() {

    // Remote
    suspend fun registerReferralCode(
        accessToken: String,
        referral: HashMap<String, String>
    ) = safeApiCall(Dispatchers.IO) {
        userInfoService.registerReferralCode(accessToken, referral)
    }

}