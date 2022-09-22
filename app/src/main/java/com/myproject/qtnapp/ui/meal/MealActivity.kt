package com.myproject.qtnapp.ui.meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.databinding.ActivityMealBinding
import com.myproject.qtnapp.di.SharedPreference
import org.koin.android.ext.android.inject

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private var categoryData : FoodByCategoryEntity? = null
    private val viewModel: MealViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
        categoryData= intent.getParcelableExtra<FoodByCategoryEntity>(CATEGORY_DATA)
        viewModel.getMealDetail(categoryData?.id ?: "")
        init()
    }

    private fun init() {
        if(categoryData!!.isFav) {
            binding.ivFavorite.isGone = false
            binding.ivNonFavorite.isGone = true
        } else {
            binding.ivFavorite.isGone = true
            binding.ivNonFavorite.isGone = false
        }
        binding.ivFavorite.setOnClickListener {
            categoryData?.isFav = false
            categoryData?.let { it1 -> viewModel.updateFood(it1) }
            binding.ivFavorite.isGone = true
            binding.ivNonFavorite.isGone = false
        }
        binding.ivNonFavorite.setOnClickListener {
            categoryData?.isFav = true
            categoryData?.let { it1 -> viewModel.updateFood(it1) }
            binding.ivFavorite.isGone = false
            binding.ivNonFavorite.isGone = true
        }
        binding.btnMakeFood.setOnClickListener {
            val sharedPreference = SharedPreference(this)
            val proTotal = sharedPreference.getProData("pro_total") + (categoryData?.pro ?: 0)
            val carbTotal = sharedPreference.getCarbData("carb_total") + (categoryData?.carb ?: 0)
            val fatTotal = sharedPreference.getFatData("fat_total") + (categoryData?.fat ?: 0)
            sharedPreference.proTotal("pro_total", proTotal)
            sharedPreference.carbTotal("carb_total", carbTotal)
            sharedPreference.fatTotal("fat_total", fatTotal)
            finish()
        }
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