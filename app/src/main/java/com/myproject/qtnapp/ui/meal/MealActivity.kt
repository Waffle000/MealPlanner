package com.myproject.qtnapp.ui.meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
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
                        tvFoodLocation.text = data.strArea
                        tvFoodInstruction.text = data.strInstructions
                        tvProMealDetail.text = categoryData?.pro.toString()
                        tvFatMealDetail.text = categoryData?.fat.toString()
                        tvCarbMealDetail.text = categoryData?.carb.toString()
                        Glide.with(this@MealActivity)
                            .load(data.strMealThumb)
                            .into(ivFoodDetail)
                    }
                    val listIngredients = mutableListOf<Ingredients?>()
                    if(data.strIngredient1.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient1, data.strMeasure1))
                    }

                    if(data.strIngredient2.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient2, data.strMeasure2))

                    }

                    if(data.strIngredient3.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient3, data.strMeasure3))

                    }

                    if(data.strIngredient4.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient4, data.strMeasure4))

                    }

                    if(data.strIngredient5.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient5, data.strMeasure5))

                    }

                    if(data.strIngredient6.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient6, data.strMeasure6))

                    }

                    if(data.strIngredient7.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient7, data.strMeasure7))

                    }

                    if(data.strIngredient8.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient8, data.strMeasure8))

                    }

                    if(data.strIngredient9.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient9, data.strMeasure9))

                    }

                    if(data.strIngredient10.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient10, data.strMeasure10))

                    }

                    if(data.strIngredient11.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient11, data.strMeasure11))

                    }

                    if(data.strIngredient12.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient12, data.strMeasure12))

                    }

                    if(data.strIngredient13.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient13, data.strMeasure13))

                    }

                    if(data.strIngredient14.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient14, data.strMeasure14))

                    }

                    if(data.strIngredient15.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient15, data.strMeasure15))

                    }

                    if(data.strIngredient16.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient16, data.strMeasure16))

                    }

                    if(data.strIngredient17.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient17, data.strMeasure17))

                    }

                    if(data.strIngredient18.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient18, data.strMeasure18))

                    }

                    if(data.strIngredient19.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient19, data.strMeasure19))

                    }

                    if(data.strIngredient20.isNullOrBlank().not()) {
                        listIngredients.add(Ingredients(data.strIngredient20, data.strMeasure20))

                    }

                    with(binding.rvIngredient) {
                        adapter = MealAdapter(listIngredients)
                        layoutManager = LinearLayoutManager(this@MealActivity)
                    }
                }
            }
        }
    }

    companion object {
        const val CATEGORY_DATA = "MealActivity.CategoryData"
    }
}