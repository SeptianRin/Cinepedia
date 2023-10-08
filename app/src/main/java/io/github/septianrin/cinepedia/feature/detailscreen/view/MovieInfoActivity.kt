package io.github.septianrin.cinepedia.feature.detailscreen.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.github.septianrin.cinepedia.R
import io.github.septianrin.cinepedia.Utils
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
        if (id != null) {
            movieInfoViewModel.loadMovieById(apiKey, this, id)
            movieInfoViewModel.movieInfoLiveData.observe(this) { item ->

                with(binding) {
                    tvTitle.text = item.title
                    tvReleaseDate.text = item.releaseDate
                    valueRuntime.text = "${item.runtime}\nMinutes"
                    valueRating.text = "${item.voteAverage} / 10"
                    valueRevenue.text = "$ ${Utils.getFormatedNumber(item.revenue)}"
                    tvOverview.text = item.overview
                    Glide.with(this@MovieInfoActivity)
                        .load(Utils.TMDB_URL_IMAGE + item.posterPath)
                        .placeholder(null)
                        .error(R.drawable.image_not_found)
                        .into(binding.ivPoster)
                    Glide.with(this@MovieInfoActivity)
                        .load(Utils.TMDB_URL_IMAGE + item.backdropPath)
                        .placeholder(null)
                        .error(R.drawable.image_not_found)
                        .into(binding.ivBackdrop)
                }
                val listFragment: List<Fragment> = listOf(
                    CastFragment(item.credits.cast),
                    TrailerFragment(item.videos.results),
                    ReviewFragment(item.reviews.results)

                )
                val infoAdapter = MovieInfoViewPager(listFragment, this)
                binding.viewPager.adapter = infoAdapter
                TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                    when (position) {
                        0 -> tab.text = "Cast"
                        1 -> tab.text = "Trailer"
                        2 -> tab.text = "Review"
                    }
                }.attach()

            }
        }

    }
}