package com.dicoding.fundamentalandroid_submission2_ikhsan.detailActivity

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.fundamentalandroid_submission2_ikhsan.api.ApiConfig
import com.dicoding.fundamentalandroid_submission2_ikhsan.database.FavoritDao
import com.dicoding.fundamentalandroid_submission2_ikhsan.database.FavoriteUser
import com.dicoding.fundamentalandroid_submission2_ikhsan.database.UserFavDatabase
import com.dicoding.fundamentalandroid_submission2_ikhsan.model.DetailuserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val userDetail = MutableLiveData<DetailuserModel>()
    private var userDao: FavoritDao?
    private var db : UserFavDatabase?

    init {
        db = UserFavDatabase.getInstance(application)
        userDao = db?.favoritDao()
    }




    fun setUserDetail(username: String) {
        ApiConfig.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailuserModel>{
                override fun onResponse(
                    call: Call<DetailuserModel>,
                    response: Response<DetailuserModel>,
                ) {
                    if (response.isSuccessful) {
                        userDetail.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailuserModel>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }
            })
    }

    fun addFavorite(username: String, id: Int, avatar_url: String, url: String)  {
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavoriteUser(
                username,
                id,
                avatar_url,
                url
            )
            userDao?.addFavorite(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun deleteFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.deleteFavorite(id)
        }
    }

    fun getUserDetail(): LiveData<DetailuserModel> {
        return userDetail
    }

}