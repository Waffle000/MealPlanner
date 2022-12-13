package com.myproject.qtnapp.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myproject.qtnapp.R
import com.myproject.qtnapp.databinding.FragmentProfileBinding
import com.myproject.qtnapp.di.SharedPreference
import com.myproject.qtnapp.ui.login.LoginActivity
import org.koin.android.ext.android.inject


class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

    private val viewModel: ProfileViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logOut()
        val email = SharedPreference(requireContext()).getEmail("email")
        viewModel.getProfile(email)
    }

    private fun logOut() {
        binding.btnLogout.setOnClickListener {
            SharedPreference(requireContext()).isLogout("isLogin")
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }

        viewModel.observeProfileSuccess().observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { data ->
                with(binding) {
                    tvNameProfile.text = data.fullName
                    tvEmailProfile.text = data.email
                    tvTelpProfile.text = data.phoneNumber
                }
            }
        }

    }
}