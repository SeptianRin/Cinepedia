package io.github.septianrin.cinepedia.feature.detailscreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.septianrin.cinepedia.databinding.FragmentReviewBinding
import io.github.septianrin.cinepedia.feature.detailscreen.models.Review
import io.github.septianrin.cinepedia.feature.detailscreen.view.adapter.ListReviewAdapter

class ReviewFragment(private val listReviews: List<Review>) : Fragment() {

    private val binding: FragmentReviewBinding by lazy {
        FragmentReviewBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listReviewAdapter = ListReviewAdapter(requireContext())
        binding.rvReview.apply {
            adapter = listReviewAdapter
        }
        listReviewAdapter.setData(listReviews)
    }
}