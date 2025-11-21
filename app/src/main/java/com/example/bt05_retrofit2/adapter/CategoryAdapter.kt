package com.example.bt05_retrofit2.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bt05_retrofit2.R
import com.example.bt05_retrofit2.model.Category

class CategoryAdapter(private val context: Context, private var array: List<Category>?) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val images: ImageView = itemView.findViewById(R.id.image_cate)
        val tenSp: TextView = itemView.findViewById(R.id.tvNameCategory)

        init {
            itemView.setOnClickListener {
                Toast.makeText(context, "Bạn đã chọn category " + tenSp.text.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = array?.get(position) ?: return
        val categoryName = category.name ?: "Unknown"
        val imageUrl = category.images ?: ""

        holder.tenSp.text = categoryName

        Log.d("CategoryAdapter", "🖼️ Loading item $position: $categoryName")
        Log.d("CategoryAdapter", "🔗 Image URL: $imageUrl")

        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.images)
    }

    override fun getItemCount(): Int {
        val count = array?.size ?: 0
        Log.d("CategoryAdapter", "📊 Total items: $count")
        return count
    }

    fun updateData(newArray: List<Category>) {
        this.array = newArray
        notifyDataSetChanged()
    }
}
