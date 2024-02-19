package com.example.myutil.data.remote.model.home

import com.google.gson.annotations.SerializedName

data class TrendingListResponse(
    @SerializedName("code") val code :String,
    @SerializedName("msg") val msg:String,
    @SerializedName("data") val data:TrendingListDto,
)

data class TrendingListDto(
    @SerializedName("searchList")val trendingList : List<TrendingData>,
    @SerializedName("listSize")val listSize : Int
)

data class TrendingData(
    @SerializedName("hashtagId")val hashtagId : Int,
    @SerializedName("tag")val tag : String
)