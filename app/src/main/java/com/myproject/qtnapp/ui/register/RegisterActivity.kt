package com.myproject.qtnapp.ui.register

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.databinding.ActivityRegisterBinding
import com.myproject.qtnapp.ui.login.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity(), RegisterView {

    private lateinit var binding: ActivityRegisterBinding

    private var format: String? = null

    private val presenter: RegisterPresenter by inject {
        parametersOf(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.btnRegister.setOnClickListener {
            validation()
        }

        binding.tvBirtdateRegister.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val dateTime = Calendar.getInstance()
            DatePickerDialog(
                this, R.style.DialogTheme, DatePickerDialog.OnDateSetListener()
                { view, year, month, dayOfMonth ->
                    dateTime.set(year, month, dayOfMonth)
                    format = SimpleDateFormat("dd/MM/yyyy").format(dateTime.time)
                    binding.tvBirtdateRegister.text = format
                },
                currentDate[Calendar.YEAR],
                currentDate[Calendar.MONTH],
                currentDate[Calendar.DATE]
            ).show()
        }
    }

    private fun validation() {
        if (binding.etEmailRegister.text.isNullOrBlank()) {
            binding.etEmailRegister.error = "Email Masih Kosong"
        }

        if(binding.etFullnameRegister.text.isNullOrBlank()) {
            binding.etFullnameRegister.error = "Nama Masih Kosong"
        }

        if (binding.etPhoneRegister.text.isNullOrBlank()) {
            binding.etPhoneRegister.error = "Nomor Telepon Masih Kosong"
        }

        if (binding.etPasswordRegister.text.isNullOrBlank()) {
            binding.etPasswordRegister.error = "Password Masih Kosong"
        } else if (!validationPassword(binding.etPasswordRegister.text.toString().trim())) {
            Toast.makeText(
                this,
                "Password harus menggunakan minimal 8 karakter, uppercase, lowercase, angka, dan symbol",
                Toast.LENGTH_SHORT
            ).show()
        }


        if (!binding.etConfirmPasswordRegister.text.toString().equals(binding.etPasswordRegister.text.toString())) {
            binding.etConfirmPasswordRegister.error = "Password Tidak Sama"
        }

        if (format.isNullOrBlank()) {
            "Tanggal Lahir Masih Kosong"
        }

        if (!binding.etEmailRegister.text.isNullOrBlank() && !binding.etPhoneRegister.text.isNullOrBlank() && !binding.etPasswordRegister.text.isNullOrBlank() && validationPassword(
                binding.etPasswordRegister.text.toString().trim()
            ) && (binding.etConfirmPasswordRegister.text.toString() == binding.etPasswordRegister.text.toString() && !format.isNullOrBlank())
        ) {
            sendDataToDatabase()
        }
    }

    private fun sendDataToDatabase() {
        val user = UserEntity(
            id = 0,
            fullName = binding.etFullnameRegister.text.toString(),
            email = binding.etEmailRegister.text.toString(),
            phoneNumber = binding.etPhoneRegister.text.toString(),
            password = binding.etPasswordRegister.text.toString(),
            birthdate = format,
            newUser = true
        )

        presenter.insertUser(user)
    }

    private fun validationPassword(password: String): Boolean {
        val passwordPattern = Pattern.compile(
            "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +
                "$"
        );
        val match = passwordPattern.matcher(password)
        return match.matches()
    }

    override fun onError(t: Throwable) {
        Toast.makeText(this, "Error $t", Toast.LENGTH_SHORT).show()
    }

    override fun successInsert(success: Boolean) {
        if(success) {
            finish()
        }
    }
}