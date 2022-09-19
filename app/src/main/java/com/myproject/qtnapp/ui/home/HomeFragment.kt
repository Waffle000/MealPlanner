package com.myproject.qtnapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.databinding.FragmentHomeBinding
import com.myproject.qtnapp.di.SharedPreference
import com.myproject.qtnapp.ui.calorie.CalorieActivity
import com.myproject.qtnapp.ui.meal.MealActivity
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(), HomeHorizontalAdapter.onItemClick {

    private val viewModel: HomeViewModel by inject()

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
        observeData()
        binding.tvTitleCalories.setOnClickListener {
            startActivity(Intent(activity, CalorieActivity::class.java))
        }
        val categoryData = SharedPreference(requireContext()).getCategoryData("category")
        if (categoryData != null) {
            viewModel.getFoodByCategory(categoryData)
        }
    }

    private fun observeData() {
        with(viewModel) {
            observeSuccessGetFood().observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let { data ->
                    setupRv(data)
                }
            }
        }
    }

    private fun setupRv(list: List<FoodByCategoryEntity>) {
        val categoryData = SharedPreference(requireContext()).getCategoryData("category")
        with(binding.rvMealByCategory) {
            adapter = categoryData?.let { HomeAdapter(it, list, this@HomeFragment) }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setItemClickFood(data: FoodByCategoryEntity, position: Int) {
        startActivity(Intent(activity, MealActivity::class.java).putExtra(MealActivity.CATEGORY_DATA, data))
    }
}