package io.github.septianrin.cinepedia.feature.homescreen.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.github.septianrin.cinepedia.databinding.ActivityMainBinding
import io.github.septianrin.cinepedia.feature.homescreen.viewmodels.HomescreenViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val homescreenViewModel: HomescreenViewModel by viewModels()

    @Inject
    lateinit var apiKey: String

    private val gridListMovieAdapter = GridListMovieAdapter(this)
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvDiscoverMovies.apply {
            adapter = gridListMovieAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }


        homescreenViewModel.loadDiscoverMoviesByPopular(apiKey, applicationContext)
        homescreenViewModel.moviesLiveData.observe(this) { listMovies ->
            gridListMovieAdapter.addData(listMovies.results)
            isLoading = false
        }

        binding.rvDiscoverMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()


                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    isLoading = true
                    homescreenViewModel.incrementPages()
                    homescreenViewModel.loadDiscoverMoviesByPopular(apiKey, applicationContext)
                }
            }
        })


    }
}