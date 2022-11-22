package com.example.datastoreapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.datastoreapp.databinding.ActivityMainBinding
import com.example.datastoreapp.viewmodel.MainViewModel
import com.example.datastoreapp.viewmodel.MainViewModel.*
import com.example.datastoreapp.viewmodel.MainViewModel.MainStates.*
import com.example.datastoreapp.viewmodel.StoreType

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = MainViewModel()
        viewModel.getValue().observe({ lifecycle }, ::updateUI)
        setListeners()
    }

    private fun updateUI(data: MainData) {
        when (data.state) {
            GO_TO_SHARED_PREFERENCES_VIEW -> {} //start Activity() to shared preferences
            GO_TO_PROTO_DATASTORE_VIEW -> TODO() //start Activity() to proto datastore
            GO_TO_PREFERENCES_DATASTORE_VIEW -> TODO() //start Activity() to preferences datastore
        }
    }

    private fun setListeners() = with(binding) {
        btnSp.setOnClickListener { viewModel.onButtonPressed(StoreType.SHARED_PREFERENCES) }
        btnPds.setOnClickListener { viewModel.onButtonPressed(StoreType.PREFERENCES_DATASTORE) }
        btnPrds.setOnClickListener { viewModel.onButtonPressed(StoreType.PROTO_DATASTORE) }
    }
}
