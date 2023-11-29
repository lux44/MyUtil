package com.example.myutil.utils

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.GzipSource
import okio.buffer
import java.io.IOException

class GzipInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request.Builder = chain.request().newBuilder()
        newRequest.addHeader("Accept-Encoding", "gzip")

        val response = chain.proceed(newRequest.build())
        return if (isGzipped(response)!!) {
            unzip(response)!!
        } else response
    }

    @Throws(IOException::class)
    private fun unzip(response: Response): Response? {
        //Timber.d("gzipinterceptor unzip")
        if (response.body == null) {
            return response
        }
        //Timber.d("gzipinterceptor unzip!")
        val gzipSource = GzipSource(response.body!!.source())
        val bodyString: String = gzipSource.buffer().readUtf8()
        val responseBody: ResponseBody = bodyString.toResponseBody(response.body!!.contentType())
        val strippedHeaders: Headers = response.headers.newBuilder()
            .removeAll("Content-Encoding")
            .removeAll("Content-Length")
            .build()
        return response.newBuilder()
            .headers(strippedHeaders)
            .body(responseBody)
            .message(response.message)
            .build()
    }

    private fun isGzipped(response: Response): Boolean? {
        return response.header("Content-Encoding") != null && response.header("Content-Encoding")
            .equals("gzip")
    }
}