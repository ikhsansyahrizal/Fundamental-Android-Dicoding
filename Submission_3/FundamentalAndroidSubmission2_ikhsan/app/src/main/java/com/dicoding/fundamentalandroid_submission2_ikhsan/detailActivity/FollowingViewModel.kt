package com.dicoding.fundamentalandroid_submission2_ikhsan.detailActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.fundamentalandroid_submission2_ikhsan.api.ApiConfig
import com.dicoding.fundamentalandroid_submission2_ikhsan.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    val listFollower = MutableLiveData<ArrayList<User>>()

    fun setListFollowing(username: String) {
        ApiConfig.apiInstance
            .getUserFollowing(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>,
                ) {
                    if (response.isSuccessful) {
                        listFollower.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }
            })
    }

    fun getListFollowing(): LiveData<ArrayList<User>> {
        return listFollower
    }


}