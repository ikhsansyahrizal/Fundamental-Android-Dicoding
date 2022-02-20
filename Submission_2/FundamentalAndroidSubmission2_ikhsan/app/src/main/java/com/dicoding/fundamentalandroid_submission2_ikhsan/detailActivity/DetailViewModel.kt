package com.dicoding.fundamentalandroid_submission2_ikhsan.detailActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.fundamentalandroid_submission2_ikhsan.api.ApiConfig
import com.dicoding.fundamentalandroid_submission2_ikhsan.model.DetailuserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    val userDetail = MutableLiveData<DetailuserModel>()


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

    fun getUserDetail(): LiveData<DetailuserModel> {
        return userDetail
    }

}