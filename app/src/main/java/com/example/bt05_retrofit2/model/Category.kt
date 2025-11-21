package com.example.bt05_retrofit2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("images")
    var images: String? = null,

    @SerializedName("description")
    var description: String? = null
) : Serializable
