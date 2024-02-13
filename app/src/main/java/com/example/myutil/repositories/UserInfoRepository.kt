package com.example.myutil.repositories

import com.example.myutil.data.remote.api.UserInfoService
import javax.inject.Inject

class UserInfoRepository @Inject constructor(
    private val userInfoService: UserInfoService,
) {
}