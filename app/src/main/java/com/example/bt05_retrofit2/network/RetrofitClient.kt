package com.example.bt05_retrofit2.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    val instance: ApiService by lazy {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("http://app.iotstar.vn:8081/appfoods/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        retrofit!!.create(ApiService::class.java)
    }
}