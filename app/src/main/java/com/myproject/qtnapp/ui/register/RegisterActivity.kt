package com.myproject.qtnapp.ui.register

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.databinding.ActivityRegisterBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity(), RegisterView {

    private lateinit var binding: ActivityRegisterBinding

    private var format: String? = null

    private val presenter : RegisterPresenter by inject {
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
            Log.e("TAG", "${binding.etPasswordRegister.text} && ${binding.etConfirmPasswordRegister.text}")
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
           binding.etConfirmPasswordRegister.text.toString() != binding.etPasswordRegister.text.toString() -> binding.etConfirmPasswordRegister.error = "Password tidak sama"
            format.isNullOrBlank() -> binding.tvBirtdateRegister.error = "Tanggal Lahir Masih Kosong"
            else -> sendDataToDatabase()
        }
    }

    private fun sendDataToDatabase() {
        val user = UserEntity(
            id = 0,
            fullName = binding.etFullnameRegister.text.toString(),
            email = binding.etEmailRegister.text.toString(),
            phoneNumber = binding.etPhoneRegister.text.toString().toInt(),
            password = binding.etPasswordRegister.text.toString(),
            birthdate = format,
            newUser = false
        )

        presenter.insertUser(user)
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

    override fun successInsert(success: Boolean) {

    }
}