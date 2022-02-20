package com.dicoding.fundamentalandroid_submission2_ikhsan.model

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("avatar_url")
    val avatar_url: String
)