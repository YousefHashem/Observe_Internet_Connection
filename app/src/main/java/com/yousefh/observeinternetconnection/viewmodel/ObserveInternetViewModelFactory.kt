package com.yousefh.observeinternetconnection.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yousefh.observeinternetconnection.repository.ConnectivityRepository

class ObserveInternetViewModelFactory(private val connectivityRepository: ConnectivityRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ObserveInternetViewModel::class.java)) {
            return ObserveInternetViewModel(connectivityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}