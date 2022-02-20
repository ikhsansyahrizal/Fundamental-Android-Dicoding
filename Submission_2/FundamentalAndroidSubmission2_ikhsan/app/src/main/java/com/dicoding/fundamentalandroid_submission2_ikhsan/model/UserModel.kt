package com.dicoding.fundamentalandroid_submission2_ikhsan.model

import com.google.gson.annotations.SerializedName

data class  UserModel(
    @field:SerializedName("items")
    val item : ArrayList<User>


)
