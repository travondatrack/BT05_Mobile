package com.example.bt05_retrofit2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bt05_retrofit2.adapter.CategoryAdapter
import com.example.bt05_retrofit2.model.Category
import com.example.bt05_retrofit2.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rc_category)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CategoryAdapter(this, emptyList())
        recyclerView.adapter = adapter

        fetchCategories()
    }

    private fun fetchCategories() {
        val call = RetrofitClient.instance.getCategoryAll()
        call.enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if (response.isSuccessful) {
                    val list = response.body() ?: emptyList()
                    adapter.updateData(list)
                } else {
                    Log.e("MainActivity", "API error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Log.e("MainActivity", "API failure", t)
            }
        })
    }
}