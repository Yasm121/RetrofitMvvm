package com.example.phnnassignment.retrofit

import com.example.phnnassignment.data.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("users?page=1")
    fun getCountryList(): Call<List<CountryModel>>
}