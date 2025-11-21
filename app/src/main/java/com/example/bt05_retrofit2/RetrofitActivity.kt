package com.example.bt05_retrofit2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bt05_retrofit2.adapter.CategoryAdapter
import com.example.bt05_retrofit2.model.Category
import com.example.bt05_retrofit2.network.ApiService
import com.example.bt05_retrofit2.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {

    private lateinit var rcCate: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.title = "Danh sách món ăn"

        rcCate = findViewById(R.id.rc_category)
        apiService = RetrofitClient.instance

        getCategory()
    }

    private fun getCategory() {

        apiService.getCategoryAll().enqueue(object : Callback<List<Category>> {

            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                val data = response.body()

                if (!response.isSuccessful || data.isNullOrEmpty()) {
                    Log.e("RetrofitActivity", "API lỗi hoặc rỗng")
                    return
                }

                categoryAdapter = CategoryAdapter(this@RetrofitActivity, data)
                rcCate.apply {
                    layoutManager = LinearLayoutManager(
                        this@RetrofitActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    adapter = categoryAdapter
                    setHasFixedSize(true)
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Log.e("RetrofitActivity", "Network Error: ${t.message}")
            }
        })
    }
}
