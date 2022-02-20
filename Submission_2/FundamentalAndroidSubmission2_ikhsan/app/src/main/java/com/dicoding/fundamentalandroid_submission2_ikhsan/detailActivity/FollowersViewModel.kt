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

class FollowersViewModel: ViewModel() {
    val listFollower = MutableLiveData<ArrayList<User>>()

    fun setListFollowers(username: String) {
        ApiConfig.apiInstance
            .getUserFollowers(username)
            .enqueue(object : Callback<ArrayList<User>>{
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

    fun getListFollowers(): LiveData<ArrayList<User>> {
        return listFollower
    }


}