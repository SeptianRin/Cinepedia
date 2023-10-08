package io.github.septianrin.cinepedia.feature.detailscreen.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.septianrin.cinepedia.R
import io.github.septianrin.cinepedia.Utils
import io.github.septianrin.cinepedia.Utils.shimmer
import io.github.septianrin.cinepedia.databinding.ReviewListItemBinding
import io.github.septianrin.cinepedia.feature.detailscreen.models.Review


class ListReviewAdapter(private val context: Context) :
    RecyclerView.Adapter<ListReviewAdapter.ViewHolder>() {

    private var data: List<Review> = mutableListOf()

    fun setData(newData: List<Review>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListReviewAdapter.ViewHolder {
        val binding = ReviewListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListReviewAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ReviewListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Review) {
            with(binding) {
                Glide.with(context)
                    .load(Utils.TMDB_URL_IMAGE + item.authorDetails.avatarPath)
                    .placeholder(shimmer())
                    .error(R.drawable.image_not_found)
                    .into(ivAvatar)
                tvTitleReview.text = item.author
                tvSubtitleReview.text = item.content
                btnReadMore.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                    context.startActivity(browserIntent)
                }
            }
        }

    }
}