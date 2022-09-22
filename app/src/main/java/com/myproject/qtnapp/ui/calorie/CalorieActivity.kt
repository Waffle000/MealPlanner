package com.myproject.qtnapp.ui.calorie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myproject.qtnapp.R
import com.myproject.qtnapp.databinding.ActivityCalorieBinding
import com.myproject.qtnapp.di.SharedPreference

class CalorieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalorieBinding

    private var totalCalorie: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalorieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        var gender = 0
        binding.rgGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.rg_laki -> gender = 1
                R.id.rg_perempuan -> gender = 2
                else -> "Tidak Memilih Gender"
            }
        }
        binding.btnHitung.setOnClickListener {
            val beratBadan = binding.etBeratBadan.text.toString().toFloat()
            val tinggiBadan = binding.etTinggiBadan.text.toString().toFloat()
            val usia = binding.etUmur.text.toString().toFloat()

            if (gender == 1) {
                totalCalorie = (66.5 + (13.75 * beratBadan) + (5.003 * tinggiBadan) - (6.75 * usia)).toInt()
            } else if (gender == 2) {
                totalCalorie = (65.51 + (9.563 * beratBadan) + (1.850 * tinggiBadan) - (4.676 * usia)).toInt()
            } else {
                Toast.makeText(this, "Data tidak valid", Toast.LENGTH_SHORT).show()
            }

            binding.tvTotalCal.text = totalCalorie.toString()
        }

        binding.btnSave.setOnClickListener {
            if(totalCalorie != 0) {
                val sharedPreference = SharedPreference(this)
                sharedPreference.calorieTotal("calorie_total", totalCalorie)
                finish()
            } else {
                Toast.makeText(this, "Kalori belum di isi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}