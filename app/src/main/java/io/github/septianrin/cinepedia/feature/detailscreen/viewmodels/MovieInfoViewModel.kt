package io.github.septianrin.cinepedia.feature.detailscreen.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.septianrin.cinepedia.feature.detailscreen.models.MovieInfo
import io.github.septianrin.cinepedia.networking.APIService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val apiService: APIService,
) : ViewModel() {

    val movieInfoLiveData: MutableLiveData<MovieInfo> = MutableLiveData()

    fun loadMovieById(apiKey: String, context: Context, movieId: String) {
        viewModelScope.launch {
            try {
                Log.e("loadDiscoverMoviesByPopular: ", movieId)
                val response = apiService.getMovieById(apiKey, movieId)
                if (response.isSuccessful) {
                    movieInfoLiveData.value = response.body()
                } else {
                    when (response.code()) {
                        400 -> Toast.makeText(context, "Bad Request", Toast.LENGTH_SHORT).show()
                        401 -> Toast.makeText(context, "Unauthorized", Toast.LENGTH_SHORT).show()
                        500 -> Toast.makeText(context, "Bad Gateway", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("loadDiscoverMoviesByPopular: ", e.toString())
            }
        }
    }
}