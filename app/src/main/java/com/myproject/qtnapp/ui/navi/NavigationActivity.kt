package com.myproject.qtnapp.ui.navi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ActivityNavigator
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.myproject.qtnapp.R
import com.myproject.qtnapp.databinding.ActivityNavigationBinding
import com.myproject.qtnapp.databinding.FragmentHomeBinding
import com.myproject.qtnapp.di.SharedPreference
import com.myproject.qtnapp.ui.login.LoginActivity

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.tvTitleTodayReport.setOnClickListener{
//            SharedPreference(this).isLogout("isLogin")
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }

        setupNavi()
    }

    private fun setupNavi() {
        val navController = findNavController(R.id.fg_navi)
        binding.bnvNavi.setupWithNavController(navController)
    }
}