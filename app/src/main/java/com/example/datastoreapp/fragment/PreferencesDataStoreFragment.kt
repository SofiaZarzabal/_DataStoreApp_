package com.example.datastoreapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.datastoreapp.R
import com.example.datastoreapp.databinding.FragmentPreferencesDataStoreBinding
import com.example.datastoreapp.storetype.PreferencesDataStore
import com.example.datastoreapp.viewmodel.PreferencesDataStoreViewModel

class PreferencesDataStoreFragment : Fragment(R.layout.fragment_preferences_data_store) {
    private lateinit var binding: FragmentPreferencesDataStoreBinding
    private var viewModel: PreferencesDataStoreViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPreferencesDataStoreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            viewModel = PreferencesDataStoreViewModel(PreferencesDataStore(it)).also { viewModel ->
                viewModel.liveData.observe(viewLifecycleOwner) { age ->
                    binding.tvShowInfo.text = getString(R.string.txt_show_info, age.toString())
                }
            }
        }
        configView()
    }

    private fun configView() = with(binding) {
        btnSave.setOnClickListener {
            viewModel?.saveInfoPressed(ageEditText.text.toString())
            ageEditText.text?.clear()
            tvShowInfo.visibility = GONE
        }

        btnLoad.setOnClickListener {
            viewModel?.loadInfoPressed()
            tvShowInfo.visibility = VISIBLE
        }
    }
}
