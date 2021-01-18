package com.example.aqtbd7.Models

import com.google.gson.annotations.SerializedName

data class MovieDbResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int
)