package com.myproject.qtnapp.ui.calorie

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myproject.qtnapp.R
import com.myproject.qtnapp.databinding.ActivityCalorieBinding

class CalorieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalorieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalorieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        var gender = 0
        binding.rgGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.rg_laki -> gender = 1
                R.id.rg_perempuan -> gender = 2
                else -> "Tidak Memilih Gender"
            }
        }

        Log.e("TAG", "init: $gender", )
        binding.btnSave.setOnClickListener {
            val beratBadan = binding.etBeratBadan.text.toString().toFloat()
            val tinggiBadan = binding.etTinggiBadan.text.toString().toFloat()
            val usia = binding.etUmur.text.toString().toFloat()

            if (gender == 1) {
                binding.tvTotalCal.text = (66.5 + (13.75 * beratBadan) + (5.003 * tinggiBadan) - (6.75 * usia)).toString()
            } else if (gender == 2) {
                binding.tvTotalCal.text = (65.51 + (9.563 * beratBadan) + (1.850 * tinggiBadan) - (4.676 * usia)).toString()
            } else {
                Toast.makeText(this, "Data tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}