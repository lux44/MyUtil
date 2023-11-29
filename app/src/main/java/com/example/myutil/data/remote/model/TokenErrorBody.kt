package com.example.myutil.data.remote.model

data class TokenErrorBody(
    val code: String?,
    val msg: String?,
    val dataObj: Token?
)

data class Token(
    val accessToken: String?
)
