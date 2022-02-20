package com.dicoding.fundamentalandroid_submission2_ikhsan.FavoriteActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicoding.fundamentalandroid_submission2_ikhsan.database.FavoritDao
import com.dicoding.fundamentalandroid_submission2_ikhsan.database.FavoriteUser
import com.dicoding.fundamentalandroid_submission2_ikhsan.database.UserFavDatabase

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: FavoritDao?
    private var db : UserFavDatabase?

    init {
        db = UserFavDatabase.getInstance(application)
        userDao = db?.favoritDao()
    }

    fun getFavUser(): LiveData<List<FavoriteUser>>? {
        return userDao?.getAllFavUser()
    }


}