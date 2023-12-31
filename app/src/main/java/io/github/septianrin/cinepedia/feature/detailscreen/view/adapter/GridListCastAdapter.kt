package io.github.septianrin.cinepedia.feature.detailscreen.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.septianrin.cinepedia.R
import io.github.septianrin.cinepedia.Utils
import io.github.septianrin.cinepedia.Utils.shimmer
import io.github.septianrin.cinepedia.databinding.ItemCastListBinding
import io.github.septianrin.cinepedia.feature.detailscreen.models.Cast

class GridListCastAdapter(private val context: Context) :
    RecyclerView.Adapter<GridListCastAdapter.ViewHolder>() {

    private var data: MutableList<Cast> = mutableListOf()

    fun setData(newData: List<Cast>) {
        data = newData.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridListCastAdapter.ViewHolder {
        val binding = ItemCastListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridListCastAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: ItemCastListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cast) {
            Glide.with(context)
                .load(Utils.TMDB_URL_IMAGE + item.profilePath)
                .placeholder(shimmer())
                .error(R.drawable.image_not_found)
                .into(binding.ivPoster)
            binding.tvTitleMovie.text = item.name
        }

    }
}