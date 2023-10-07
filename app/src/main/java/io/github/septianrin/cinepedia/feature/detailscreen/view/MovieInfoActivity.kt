package io.github.septianrin.cinepedia.feature.detailscreen.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.septianrin.cinepedia.databinding.ActivityMovieInfoBinding
import io.github.septianrin.cinepedia.feature.detailscreen.viewmodels.MovieInfoViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MovieInfoActivity : AppCompatActivity() {

    private val binding: ActivityMovieInfoBinding by lazy {
        ActivityMovieInfoBinding.inflate(layoutInflater)
    }

    private val movieInfoViewModel: MovieInfoViewModel by viewModels()

    @Inject
    lateinit var apiKey: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getStringExtra("MOVIE_ID")
        binding.textId.text = id

        if (id != null) {
            movieInfoViewModel.loadMovieById(apiKey, this, id)
            movieInfoViewModel.movieInfoLiveData.observe(this) {
                Log.e("onCreate: ", "$it")
                binding.textId.text = it.title

            }
        }

    }
}