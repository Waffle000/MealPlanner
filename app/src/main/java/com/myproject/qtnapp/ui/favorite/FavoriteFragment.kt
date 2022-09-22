package com.myproject.qtnapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.qtnapp.R
import com.myproject.qtnapp.databinding.FragmentFavoriteBinding
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {
    private lateinit var binding:  FragmentFavoriteBinding
    private val viewModel : FavoriteViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteFood()
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            observeFoodFavorite().observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let { data ->
                    with(binding.rvFavorite) {
                        adapter = FavoriteAdapter(data)
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }
        }
    }
}