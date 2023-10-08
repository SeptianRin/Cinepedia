package io.github.septianrin.cinepedia.feature.homescreen.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.septianrin.cinepedia.R
import io.github.septianrin.cinepedia.Utils
import io.github.septianrin.cinepedia.databinding.MovieListItemBinding
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
        val binding = MovieListItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MovieInfoActivity::class.java)
                .putExtra("MOVIE_ID", item.id.toString())
            context.startActivity(intent)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            Glide.with(context)
                .load(Utils.TMDB_URL_IMAGE + item.posterPath)
                .placeholder(null)
                .error(R.drawable.image_not_found)
                .into(binding.ivPoster)
            binding.tvTitleMovie.text = item.title
        }
    }
}
