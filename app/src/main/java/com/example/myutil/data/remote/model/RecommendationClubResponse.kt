package com.example.myutil.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecommendationClubResponse (
    @field:SerializedName("clubList") val clubList: List<RecommendationClub>,
    @field:SerializedName("listSize") val listSize: Int
)