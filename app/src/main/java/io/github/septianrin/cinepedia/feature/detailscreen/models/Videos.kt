package io.github.septianrin.cinepedia.feature.detailscreen.models


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("results")
    val results: List<Video>
)