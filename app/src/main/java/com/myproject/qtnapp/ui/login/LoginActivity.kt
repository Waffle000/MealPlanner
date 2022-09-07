package com.myproject.qtnapp.ui.login

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.databinding.ActivityLoginBinding
import com.myproject.qtnapp.ui.category.CategoryAdapter
import com.myproject.qtnapp.ui.navi.NavigationActivity
import com.myproject.qtnapp.ui.register.RegisterActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding

    private val presenter : LoginPresenter by inject {
        parametersOf(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        with(binding) {
            btnLogin.setOnClickListener {

                if(etEmailLogin.text.isNullOrBlank()) {
                    etEmailLogin.error = "Email Masih Kosong"
                }

                if(etPassLogin.text.isNullOrBlank()) {
                    etPassLogin.error = "Password Masih Kosong"
                }

                if(!etEmailLogin.text.isNullOrBlank() && !etPassLogin.text.isNullOrBlank()) {
                    presenter.getDataLogin(etEmailLogin.text.toString(), etPassLogin.text.toString())
                }
            }

            btnLoginToRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    override fun successLogin(data: UserEntity) {
        startActivity(Intent(this, NavigationActivity::class.java))
    }
}