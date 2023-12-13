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
import com.example.datastoreapp.databinding.FragmentProtoDataStoreBinding
import com.example.datastoreapp.storetype.createUserDataStore
import com.example.datastoreapp.viewmodel.ProtoDataStoreViewModel

class ProtoDataStoreFragment : Fragment(R.layout.fragment_proto_data_store) {
    private lateinit var binding: FragmentProtoDataStoreBinding
    private var viewModel: ProtoDataStoreViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentProtoDataStoreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            binding.apply {
                viewModel = ProtoDataStoreViewModel(it.createUserDataStore).also { viewModel ->
                    viewModel.userInfoLiveData.observe(viewLifecycleOwner) { user ->
                        tvShowInfo.text = getString(
                            R.string.txt_show_completed_info,
                            user.name,
                            user.age.toString()
                        )
                    }
                }
            }
        }
        configView()
    }

    private fun configView() = with(binding) {
        btnSave.setOnClickListener {
            viewModel?.saveInfoPressed(
                nameEditText.text.toString(),
                ageEditText.text.toString()
            )
            nameEditText.text?.clear()
            ageEditText.text?.clear()
            tvShowInfo.visibility = GONE
        }

        btnLoad.setOnClickListener {
            viewModel?.loadInfoPressed()
            tvShowInfo.visibility = VISIBLE
        }
    }
}
