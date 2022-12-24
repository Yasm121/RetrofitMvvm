package com.example.phnnassignment.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class RetrofitInstance {
    companion object {
        val BASE_URL = "https://reqres.in/api/"//v2/

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}