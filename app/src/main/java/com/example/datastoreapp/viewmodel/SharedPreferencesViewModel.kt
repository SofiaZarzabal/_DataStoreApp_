package com.example.datastoreapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datastoreapp.storetype.SharedPreferencesStore

class SharedPreferencesViewModel(private val sharedPreferencesStorage: SharedPreferencesStore) :
    ViewModel() {

    private val mutableLiveData = MutableLiveData<String>()
    val liveData: LiveData<String>
        get() = mutableLiveData

    fun saveInfoPressed(userName: String) {
        sharedPreferencesStorage.saveName(userName)
    }

    fun loadInfoPressed() {
        mutableLiveData.value = sharedPreferencesStorage.getName()
    }
}
