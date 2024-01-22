package com.example.myutil.data.remote.model

import com.google.gson.annotations.SerializedName

data class Language(
    @field:SerializedName("nameKr") val nameKr: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("langCode") val langCode: String
)

fun Language.toLanguageEntity() = Language(
    nameKr = nameKr,
    name = name,
    langCode = langCode
)