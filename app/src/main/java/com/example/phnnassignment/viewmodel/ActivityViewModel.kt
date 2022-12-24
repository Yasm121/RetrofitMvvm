package com.example.phnnassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phnnassignment.data.CountryModel
import com.example.phnnassignment.retrofit.RetrofitInstance
import com.example.phnnassignment.retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ActivityViewModel: ViewModel(){

    lateinit var liveDataList: MutableLiveData<List<CountryModel>>

    init {
        liveDataList = MutableLiveData()

    }

    fun getLiveDataObeserver(): MutableLiveData<List<CountryModel>>{
        return liveDataList
    }
    fun makeAPICall(){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
        val retroservice = retrofitInstance.create(RetrofitInterface::class.java)
        val call = retroservice.getCountryList()
        call.enqueue(object : Callback<List<CountryModel>>{
            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                liveDataList.postValue(response.body())
            }

        })


     }
}

