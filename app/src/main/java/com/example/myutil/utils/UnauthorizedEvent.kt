package com.example.myutil.utils

import com.example.myutil.data.remote.model.TokenErrorBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

object UnauthorizedEvent {
    const val TYPE_UNAUTH_NOTI = 0
    const val TYPE_UNAUTH_WITHOUT_NOTI = 1

    val events = MutableSharedFlow<Any>(replay = 0)
    val mutableEvents = events.asSharedFlow()

    suspend fun sendEvent(unauthorizedInfo: UnauthorizedInfo) {
        events.emit(unauthorizedInfo)
    }

    inline fun <reified T> subscribe(): Flow<T> {
        return mutableEvents.filter { it is T }.map { it as T }
    }
}

data class UnauthorizedInfo(
    val eventType: Int,
    val callApi: String,
    val errorTokenBody: TokenErrorBody?
)