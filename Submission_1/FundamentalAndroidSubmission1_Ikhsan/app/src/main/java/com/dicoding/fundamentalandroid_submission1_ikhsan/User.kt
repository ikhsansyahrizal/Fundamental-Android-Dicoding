package com.dicoding.fundamentalandroid_submission1_ikhsan

import android.os.Parcel
import android.os.Parcelable

data class User(
    var username: String?,
    var name: String?,
    var location: String?,
    var avatar: Int,
    var followers: String?,
    var following: String?,
    var company: String?,
    var repository: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeValue(avatar)
        parcel.writeString(followers)
        parcel.writeString(following)
        parcel.writeString(company)
        parcel.writeString(repository)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
