package com.yousefh.observeinternetconnection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yousefh.observeinternetconnection.repository.ConnectivityRepository

class ObserveInternetViewModel (private val connectivityRepository: ConnectivityRepository) : ViewModel() {

    val isConnected: LiveData<Boolean>
        get() = connectivityRepository.isConnected

    init {
        connectivityRepository.registerNetworkCallback()
    }

    fun onDestroy() {
        connectivityRepository.unregisterNetworkCallback()
    }

    override fun onCleared() {
        super.onCleared()
        connectivityRepository.unregisterNetworkCallback()
    }
}
