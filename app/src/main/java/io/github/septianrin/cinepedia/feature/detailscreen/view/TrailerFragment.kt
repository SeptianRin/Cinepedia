package io.github.septianrin.cinepedia.feature.detailscreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.github.septianrin.cinepedia.databinding.FragmentTrailerBinding
import io.github.septianrin.cinepedia.feature.detailscreen.models.Video
import io.github.septianrin.cinepedia.feature.detailscreen.view.adapter.GridListTrailerAdapter

class TrailerFragment(private val listTrailer: List<Video>) : Fragment() {

    private val binding: FragmentTrailerBinding by lazy {
        FragmentTrailerBinding.inflate(layoutInflater)
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
        val gridListTrailerAdapter = GridListTrailerAdapter(requireContext())
        binding.rvCast.apply {
            adapter = gridListTrailerAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        gridListTrailerAdapter.setData(listTrailer)
    }
}