package io.github.septianrin.cinepedia.feature.detailscreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.github.septianrin.cinepedia.databinding.FragmentCastBinding
import io.github.septianrin.cinepedia.feature.detailscreen.models.Cast
import io.github.septianrin.cinepedia.feature.detailscreen.view.adapter.GridListCastAdapter

class CastFragment(private val listCast: List<Cast>) : Fragment() {

    private val binding: FragmentCastBinding by lazy {
        FragmentCastBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridListCastAdapter = GridListCastAdapter(requireContext())
        binding.rvCast.apply {
            adapter = gridListCastAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
        gridListCastAdapter.setData(listCast)
    }
}