package com.myproject.qtnapp.ui.login

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.databinding.ActivityLoginBinding
import com.myproject.qtnapp.di.SharedPreference
import com.myproject.qtnapp.ui.category.CategoryActivity
import com.myproject.qtnapp.ui.navi.NavigationActivity
import com.myproject.qtnapp.ui.register.RegisterActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        observeData()
    }

    private fun init() {
        with(binding) {
            btnLogin.setOnClickListener {

                if (etEmailLogin.text.isNullOrBlank()) {
                    etEmailLogin.error = "Email Masih Kosong"
                }

                if (etPassLogin.text.isNullOrBlank()) {
                    etPassLogin.error = "Password Masih Kosong"
                }

                if (!etEmailLogin.text.isNullOrBlank() && !etPassLogin.text.isNullOrBlank()) {
                    viewModel.getDataLogin(
                        etEmailLogin.text.toString(),
                        etPassLogin.text.toString()
                    )
                }
            }

            btnLoginToRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    private fun observeData() {
        with(viewModel) {
            observeIsLogin().observe(this@LoginActivity) {
                it.getContentIfNotHandled().let { data ->
                    if(data != null) {
                        if (data.newUser) {
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    CategoryActivity::class.java
                                ).putExtra(CategoryActivity.USER_DATA, data)
                            )
                            finish()
                        } else {
                            startActivity(Intent(this@LoginActivity, NavigationActivity::class.java))
                            val sharedPreference = SharedPreference(this@LoginActivity)
                            sharedPreference.isLogin("isLogin", true)
                            sharedPreference.email("email", data.email ?: "")
                            finish()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val login = SharedPreference(this).getIsLogin("isLogin")
        if (login) {
            startActivity(Intent(this, NavigationActivity::class.java))
        }
    }
}