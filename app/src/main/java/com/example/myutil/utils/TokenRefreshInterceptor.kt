package com.example.myutil.utils

import android.os.Build
import com.example.myutil.BuildConfig
import com.example.myutil.data.remote.model.TokenErrorBody
import com.example.myutil.repositories.DataStoreKey.Companion.PREF_KEY_ACCESS_TOKEN
import com.example.myutil.repositories.DataStoreKey.Companion.PREF_KEY_LANGUAGE_CODE
import com.example.myutil.repositories.DataStoreKey.Companion.PREF_KEY_SYSTEM_COUNTRY
import com.example.myutil.repositories.DataStoreKey.Companion.PREF_KEY_UID
import com.example.myutil.repositories.DataStoreRepository
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.net.HttpURLConnection
import javax.inject.Inject

class TokenRefreshInterceptor @Inject constructor(private val dataStoreRepository: DataStoreRepository): Interceptor {

    private var accessToken: String? = ""
    private var isUnauthorized = false
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response?
        val request = chain.request()
        var newRequest: Request?

        runBlocking {
            val systemCountryCode = runBlocking {
                dataStoreRepository.getString(PREF_KEY_SYSTEM_COUNTRY) ?: ""
            }
            val appLangCode = dataStoreRepository.getString(PREF_KEY_LANGUAGE_CODE) ?: "en"

            accessToken = dataStoreRepository.getString(PREF_KEY_ACCESS_TOKEN)

            val url = request.url.newBuilder()
                .addQueryParameter(
                    ConstVariables.Config.KEY_COMMON_PARAM_APP_VERSION,
                    BuildConfig.VERSION_NAME
                )
                .addQueryParameter(
                    ConstVariables.Config.KEY_COMMON_PARAM_DEVICE,
                    ConstVariables.Config.VALUE_DEVICE_TYPE
                )
                .addQueryParameter(
                    ConstVariables.Config.KEY_COMMON_PARAM_OS_VERSION,
                    Build.VERSION.RELEASE
                )
                .addQueryParameter(
                    ConstVariables.Config.KEY_COMMON_PARAM_COUNTRY,
                    systemCountryCode
                )
                .build()

            val urlStrings = url.toString().split("/")
            var refererUrl = ""
            try {
                refererUrl = "${urlStrings[1]}/${urlStrings[2]}/${urlStrings[3]}"
            } catch (e: Exception) {
                Timber.e("${e.printStackTrace()}")
            }

            val newRequestBuilder: Request.Builder = request.newBuilder().apply {
                if (request.headers(ConstVariables.KEY_HEADER_LANG) == null) addHeader(
                    ConstVariables.KEY_HEADER_LANG,
                    appLangCode
                )
                addHeader(ConstVariables.Config.KEY_HEADER_REFERER, refererUrl)
                addHeader(
                    ConstVariables.Config.KEY_HEADER_USER_AGENT,
                    ConstVariables.Config.VALUE_HEADER_USER_AGENT
                )
            }

            newRequestBuilder.url(url)
            newRequest = if (!accessToken.isNullOrEmpty()) {
                newRequestBuilder.removeHeader(ConstVariables.KEY_ACCESS_TOKEN)
                newRequestBuilder.addHeader(ConstVariables.KEY_ACCESS_TOKEN, accessToken!!).build()
            } else {
                newRequestBuilder.build()
            }

            response = chain.proceed(newRequest!!)
            Timber.d("process# ${response?.headers}")

            if (response!!.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
                if (isUnauthorized) {
                    Timber.i("To request will be skip during token refresh")
                    return@runBlocking response
                }
                isUnauthorized = true
                var isAccessTokenRefresh = false
                var errorTokenBody: TokenErrorBody? = null
                var errorRequest: Request = newRequest!!
                Timber.d("proceed# intercept, Http_Unauthorized !!!, req = $request")

                try {
                    val responseString = response!!.peekBody(Long.MAX_VALUE).string()
                    val tokenErrorBody = Gson().fromJson(
                        responseString,
                        TokenErrorBody::class.java
                    )
                    errorTokenBody = tokenErrorBody
                    Timber.d("proceed# response : $tokenErrorBody")

                    if (tokenErrorBody.dataObj != null) {
                        tokenErrorBody.dataObj.accessToken?.let { newAccessToken ->
                            accessToken = newAccessToken
                            dataStoreRepository.putString(
                                PREF_KEY_ACCESS_TOKEN,
                                newAccessToken
                            )
                            val reNewRequest = request.newBuilder().removeHeader(ConstVariables.KEY_ACCESS_TOKEN)
                                .addHeader(ConstVariables.KEY_ACCESS_TOKEN, newAccessToken).build()
                            Timber.d("request = $reNewRequest, newAccessToken : $newAccessToken")
                            response = chain.proceed(reNewRequest)
                            isAccessTokenRefresh = true
                            isUnauthorized = false
                        }
                    }
                } catch (e: Exception) {
                    Timber.e("HTTP_UNAUTHORIZED exception : ${e.printStackTrace()}")
                }

                if (!isAccessTokenRefresh) {
                    dataStoreRepository.putString(PREF_KEY_ACCESS_TOKEN, "")
                    dataStoreRepository.putString(PREF_KEY_UID, "")
                    accessToken = ""
                    UnauthorizedEvent.sendEvent(
                        UnauthorizedInfo(
                            eventType = UnauthorizedEvent.TYPE_UNAUTH_NOTI,
                            callApi = errorRequest.url.toString(),
                            errorTokenBody = errorTokenBody
                        )
                    )
                }
            }
            isUnauthorized = false
        }
        return response!!
    }

}