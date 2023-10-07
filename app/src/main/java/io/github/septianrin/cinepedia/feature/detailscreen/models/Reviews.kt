package io.github.septianrin.cinepedia.feature.detailscreen.models


import com.google.gson.annotations.SerializedName

data class Reviews(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)