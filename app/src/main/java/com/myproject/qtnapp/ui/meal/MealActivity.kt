package com.myproject.qtnapp.ui.meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.databinding.ActivityMealBinding
import org.koin.android.ext.android.inject

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding

    private val viewModel: MealViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
        val categoryData = intent.getParcelableExtra<FoodByCategoryEntity>(CATEGORY_DATA)
        viewModel.getMealDetail(categoryData?.id ?: "")
    }

    private fun init() {

    }

    private fun observeData() {
        with(viewModel) {
            observeGetFoodDetail().observe(this@MealActivity) {
                it.getContentIfNotHandled()?.meals?.map { data ->
                    with(binding) {
                        tvFoodDetail.text = data.strMeal
                        tvFoodCategory.text = data.strCategory
                        tvFoodLocation.text = data.strArea
                        tvFoodInstruction.text = data.strInstructions
                        tvFoodTag.text = data.strTags
                        tvFoodLinkYoutube.text = data.strYoutube
                        Glide.with(this@MealActivity)
                            .load(data.strMealThumb)
                            .into(ivFoodDetail)
                    }
                }
            }
        }
    }

    companion object {
        const val CATEGORY_DATA = "MealActivity.CategoryData"
    }
}