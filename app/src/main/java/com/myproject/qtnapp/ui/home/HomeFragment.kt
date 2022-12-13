package com.myproject.qtnapp.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.local.entity.HistoryEntity
import com.myproject.qtnapp.databinding.FragmentHomeBinding
import com.myproject.qtnapp.di.SharedPreference
import com.myproject.qtnapp.ui.calorie.CalorieActivity
import com.myproject.qtnapp.ui.meal.MealActivity
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log
import kotlin.time.Duration.Companion.minutes

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
        val categoryData = SharedPreference(requireContext()).getCategoryData("category")
        if (categoryData != null) {
            viewModel.getFoodByCategory(categoryData)
        }
        setupHealth()
    }

    private fun setupHealth() {
        val currentDate = SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().time)
        val startTimeStamp = SharedPreference(requireContext()).getTimeStamp("time_data")
        if(startTimeStamp == "") {
            SharedPreference(requireContext()).timeStamp("time_data", currentDate)
        }
        val timeStamp = SharedPreference(requireContext()).getTimeStamp("time_data")
        val calorie = SharedPreference(requireContext()).getCalorieData("calorie_total")
        val pro = SharedPreference(requireContext()).getProData("pro_total")
        val carb = SharedPreference(requireContext()).getCarbData("carb_total")
        val fat = SharedPreference(requireContext()).getFatData("fat_total")
        if(!timeStamp.equals(currentDate)) {
            with(SharedPreference(requireContext())) {
                val history = HistoryEntity(
                    id = 0,
                    date = timeStamp,
                    pro = pro,
                    carb = carb,
                    fat = fat,
                    isComplete = (pro*4) + (carb*4) + (fat*2) > calorie
                )
                viewModel.insertHistory(history)
                clearPro("pro_total")
                clearCarb("carb_total")
                clearFat("fat_total")
                SharedPreference(requireContext()).timeStamp("time_data", currentDate)
            }
        }
        init(calorie, pro, carb, fat)
    }

    private fun init(calorie: Int, pro: Int, carb: Int, fat: Int) {
        with(binding) {
            tvDailyCalories.text = ((pro*4) + (carb*4) + (fat*2)).toString()
            tvCalories.text = calorie.toString()
            pbPro.max = (40.0/100.0 * calorie / 4).toInt()
            pbCarb.max = (40.0/100.0 * calorie / 4).toInt()
            pbFat.max = (20.0/100.0 * calorie / 2).toInt()
            pbPro.progress = pro
            tvPro.text = pro.toString()
            pbCarb.progress = carb
            tvCarb.text = carb.toString()
            pbFat.progress = fat
            tvFat.text = fat.toString()

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

    override fun onResume() {
        super.onResume()
        setupHealth()
    }
}