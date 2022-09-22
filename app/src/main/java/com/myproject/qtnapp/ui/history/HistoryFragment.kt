package com.myproject.qtnapp.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.qtnapp.R
import com.myproject.qtnapp.databinding.FragmentHistoryBinding
import org.koin.android.ext.android.inject


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    private val viewModel: HistoryViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHistory()
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            observeGetHistory().observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let { data ->
                    with(binding.rvHistory) {
                        adapter = HistoryAdapter(data)
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }
        }
    }
}