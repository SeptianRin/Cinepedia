package io.github.septianrin.cinepedia.feature.detailscreen.view.adapter

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.septianrin.cinepedia.R
import io.github.septianrin.cinepedia.Utils
import io.github.septianrin.cinepedia.Utils.getOrientation
import io.github.septianrin.cinepedia.Utils.shimmer
import io.github.septianrin.cinepedia.databinding.ItemTrailerListBinding
import io.github.septianrin.cinepedia.feature.detailscreen.models.Video


class GridListTrailerAdapter(private val context: Context) :
    RecyclerView.Adapter<GridListTrailerAdapter.ViewHolder>() {

    private var data: List<Video> = mutableListOf()


    fun setData(newData: List<Video>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridListTrailerAdapter.ViewHolder {
        val binding = ItemTrailerListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridListTrailerAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://${item.key}"))
            context.startActivity(intent)
        }

        holder.bind(item)

    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemTrailerListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Video) {
            if(getOrientation(context) == Configuration.ORIENTATION_LANDSCAPE ){
                Glide.with(context)
                    .load(Utils.generateYoutubeThumbnail(item.key))
                    .placeholder(shimmer())
                    .error(R.drawable.image_not_found)
                    .into(binding.ivPoster)
                binding.tvTitleMovie.apply {
                    text = item.name
                    textSize = 20F
                }
                binding.clCard.updateLayoutParams {
                    height = 700
                }
            } else {
                Glide.with(context)
                    .load(Utils.generateYoutubeThumbnail(item.key))
                    .placeholder(shimmer())
                    .error(R.drawable.image_not_found)
                    .into(binding.ivPoster)
                binding.tvTitleMovie.apply {
                    text = item.name
                    textSize = 20F
                }
            }

        }

    }
}