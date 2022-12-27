package com.example.datastoreapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.datastoreapp.R
import com.example.datastoreapp.databinding.FragmentSharedPreferencesBinding
import com.example.datastoreapp.storetype.SharedPreferencesStore
import com.example.datastoreapp.viewmodel.SharedPreferencesViewModel

class SharedPreferencesFragment : Fragment(R.layout.fragment_shared_preferences) {
    private lateinit var binding: FragmentSharedPreferencesBinding
    private var viewModel: SharedPreferencesViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSharedPreferencesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            viewModel = SharedPreferencesViewModel(SharedPreferencesStore(it))
        }
        configView()
    }

    private fun configView() = with(binding) {
        btnSave.setOnClickListener {
            viewModel?.saveInfoPressed(nameEditText.text.toString())
            nameEditText.text?.clear()
        }
        btnLoad.setOnClickListener { tvShowInfo.text = getString(R.string.txt_show_info, viewModel?.loadInfoPressed()) }
    }
}
