package io.github.septianrin.cinepedia.feature.homescreen.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.septianrin.cinepedia.feature.homescreen.models.ListMovies
import io.github.septianrin.cinepedia.networking.APIService
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomescreenViewModel @Inject constructor(
    private val apiService: APIService,
) : ViewModel() {

    val moviesLiveData: MutableLiveData<ListMovies> = MutableLiveData()
    private var pages: Int = 1

    fun incrementPages(){
        pages += 1
    }

    fun loadDiscoverMoviesByPopular(apiKey: String, context: Context) {
        viewModelScope.launch {
            try {
                Log.e("loadDiscoverMoviesByPopular: ", pages.toString())
                val response = apiService.getPopularMovieList(apiKey, pages)
                if (response.isSuccessful) {
                    moviesLiveData.value = response.body()

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