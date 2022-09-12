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


class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

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
    }

    private fun logOut() {
        binding.btnLogoUt.setOnClickListener {
            SharedPreference(requireContext()).isLogout("isLogin")
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }

    }
}