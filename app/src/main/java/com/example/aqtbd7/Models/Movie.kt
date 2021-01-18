package com.example.aqtbd7.Models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Long,
    @SerializedName("poster_path") val posterPath: String
)