package com.myproject.qtnapp.ui.register

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.databinding.ActivityRegisterBinding
import com.myproject.qtnapp.ui.login.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
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
        register()
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
            newUser = true
        )

        presenter.checkEmail(user)
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

    override fun onError(t: Throwable) {
        Toast.makeText(this, "Error $t", Toast.LENGTH_SHORT).show()
    }

    override fun successCheckEmail(success: Boolean, user: UserEntity) {
        if(success) {
            presenter.insertUser(user)
        } else {
            Toast.makeText(this, "Email sudah terdaftar", Toast.LENGTH_SHORT).show()
        }
    }

    override fun successInsert(success: Boolean) {
        if (success) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}