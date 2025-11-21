package com.example.bt05_retrofit2.network

import com.example.bt05_retrofit2.model.Category
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("categories.php")
    fun getCategoryAll(): Call<List<Category>>
}
