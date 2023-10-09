package io.github.septianrin.cinepedia

import android.content.Context
import android.graphics.drawable.Drawable
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import kotlin.math.ln
import kotlin.math.pow

object Utils {
    const val TMDB_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"

    fun generateYoutubeThumbnail(key: String): String {
        return "https://img.youtube.com/vi/$key/sddefault.jpg"
    }

    fun getFormatedNumber(count: Int): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "KMBTPE"[exp - 1])
    }

    fun shimmer(): Drawable {
        val shimmer = Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.9f)
            .setHighlightAlpha(0.8f)
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
        return shimmerDrawable
    }

    fun getOrientation(context: Context): Int {
        return context.resources.configuration.orientation
    }

}