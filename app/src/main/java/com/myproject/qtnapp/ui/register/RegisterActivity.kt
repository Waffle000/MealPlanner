package com.myproject.qtnapp.ui.register

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.databinding.ActivityRegisterBinding
import com.myproject.qtnapp.ui.login.LoginActivity
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private var format: String? = null

    private val viewModel: RegisterViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        register()
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            observeIsRegister().observe(this@RegisterActivity) {
                it.getContentIfNotHandled()?.let { success ->
                    if(success) {
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Email sudah terdaftar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun init() {
        binding.tvBirtdateRegister.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val dateTime = Calendar.getInstance()
            DatePickerDialog(
                this, DatePickerDialog.OnDateSetListener()
                { view, year, month, dayOfMonth ->
                    dateTime.set(year, month, dayOfMonth)
                    format = SimpleDateFormat("dd/MM/yyyy").format(dateTime.time)
                    binding.tvBirtdateRegister.setText(format)
                },
                currentDate[Calendar.YEAR],
                currentDate[Calendar.MONTH],
                currentDate[Calendar.DATE]
            ).show()
        }
    }

    private fun register() {
        binding.btnRegister.setOnClickListener {
            if (binding.etEmailRegister.text.isNullOrBlank()) {
                binding.etEmailRegister.error = "Email Masih Kosong"
            } else if (!binding.etEmailRegister.text.toString().isValidEmail()) {
                Toast.makeText(
                    this,
                    "Format email tidak sesuai",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (binding.etFullnameRegister.text.isNullOrBlank()) {
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


            if (!binding.etConfirmPasswordRegister.text.toString()
                    .equals(binding.etPasswordRegister.text.toString())
            ) {
                binding.etConfirmPasswordRegister.error = "Password Tidak Sama"
            }

            if (format.isNullOrBlank()) {
                binding.tvBirtdateRegister.error = "Tanggal Lahir Masih Kosong"
            } else {
                val parser = SimpleDateFormat("dd/MM/yyyy")
                val day = SimpleDateFormat("dd").format(parser.parse(format))
                val month = SimpleDateFormat("MM").format(parser.parse(format))
                val year = SimpleDateFormat("yyyy").format(parser.parse(format))
                if (getAge(year.toInt(), month.toInt(), day.toInt()) < 18) {
                    Toast.makeText(this, "Usia belum mencukupi", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            if (!binding.etEmailRegister.text.isNullOrBlank() && !binding.etPhoneRegister.text.isNullOrBlank() && !binding.etPasswordRegister.text.isNullOrBlank() && validationPassword(
                    binding.etPasswordRegister.text.toString().trim()
                ) && (binding.etConfirmPasswordRegister.text.toString() == binding.etPasswordRegister.text.toString() && !format.isNullOrBlank())
            ) {
                sendDataToDatabase()
            }
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
            newUser = true,
            totalFat = 0,
            totalPro = 0,
            totalCarb = 0
        )

        viewModel.checkingEmail(user)
    }

    fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
        return Period.between(
            LocalDate.of(year, month, dayOfMonth),
            LocalDate.now()
        ).years
    }

    private fun validationPassword(password: String): Boolean {
        val passwordPattern = Pattern.compile(
            "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{8,}" +
                "$"
        );
        val match = passwordPattern.matcher(password)
        return match.matches()
    }

    private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}