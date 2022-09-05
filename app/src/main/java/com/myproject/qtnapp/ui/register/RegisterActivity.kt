package com.myproject.qtnapp.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.myproject.qtnapp.R
import com.myproject.qtnapp.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.btnRegister.setOnClickListener {
            testing()
        }
    }

    private fun testing() {
        Log.e("TAG", "${binding.etConfirmPasswordRegister.text} DAN ${binding.etPasswordRegister.text}")
        if(!binding.etConfirmPasswordRegister.text.toString().equals(binding.etPasswordRegister.text.toString())) {
            binding.etConfirmPasswordRegister.error = "Password tidak sama"
        } else {
            Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show()
        }
    }
    private fun validation() {
        when {
            binding.etEmailRegister.text.isNullOrBlank() -> binding.etEmailRegister.error =
                "Email Masih Kosong"
            binding.etPhoneRegister.text.isNullOrBlank() -> binding.etPhoneRegister.error =
                "Nomor Telepon Masih Kosong"
            binding.etPasswordRegister.text.isNullOrBlank() -> binding.etPasswordRegister.error =
                "Password Masih Kosong"
            !validationPassword(
                binding.etPasswordRegister.text.toString().trim()
            ) -> Toast.makeText(
                this,
                "Password harus menggunakan uppercase, lowercase, angka, dan symbol",
                Toast.LENGTH_SHORT
            ).show()
           !binding.etConfirmPasswordRegister.text.equals(binding.etPasswordRegister.text) -> binding.etConfirmPasswordRegister.error = "Password tidak sama"
            else -> Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validationPassword(password: String): Boolean {
        Log.e("TAG", "$password")
        val passwordPattern = Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{8,}" +               //at least 8 characters
            "$");
        val match = passwordPattern.matcher(password)
        return match.matches()
    }
}