package com.myproject.qtnapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.qtnapp.R
import com.myproject.qtnapp.databinding.FragmentHomeBinding
import com.myproject.qtnapp.di.SharedPreference

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
    }

    private fun setupRv() {
        val categoryData = SharedPreference(requireContext()).getCategoryData("category")
        with(binding.rvMealByCategory) {
            adapter = categoryData?.let { HomeAdapter(it) }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}