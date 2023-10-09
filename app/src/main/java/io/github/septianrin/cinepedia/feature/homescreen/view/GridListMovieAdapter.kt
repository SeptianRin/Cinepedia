package io.github.septianrin.cinepedia.feature.homescreen.view

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.septianrin.cinepedia.R
import io.github.septianrin.cinepedia.Utils
import io.github.septianrin.cinepedia.Utils.getOrientation
import io.github.septianrin.cinepedia.Utils.shimmer
import io.github.septianrin.cinepedia.databinding.ItemMovieListBinding
import io.github.septianrin.cinepedia.feature.detailscreen.view.MovieInfoActivity
import io.github.septianrin.cinepedia.feature.homescreen.models.Movie

class GridListMovieAdapter(
    private val context: Context,
) :
    RecyclerView.Adapter<GridListMovieAdapter.ViewHolder>() {

    private var data: MutableList<Movie> = mutableListOf()

    fun addData(newData: List<Movie>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemMovieListBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MovieInfoActivity::class.java)
                .putExtra("MOVIE_ID", item.id.toString())
            context.startActivity(intent)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            if(getOrientation(context) == Configuration.ORIENTATION_LANDSCAPE){
                Glide.with(context)
                    .load(Utils.TMDB_URL_IMAGE + item.backdropPath)
                    .placeholder(shimmer())
                    .error(R.drawable.image_not_found)
                    .into(binding.ivPoster)
                binding.tvTitleMovie.apply {
                    text = item.title
                    textSize = 20F
                }
            } else {
                Glide.with(context)
                    .load(Utils.TMDB_URL_IMAGE + item.posterPath)
                    .placeholder(shimmer())
                    .error(R.drawable.image_not_found)
                    .into(binding.ivPoster)
                binding.tvTitleMovie.text = item.title
            }

        }
    }
}
