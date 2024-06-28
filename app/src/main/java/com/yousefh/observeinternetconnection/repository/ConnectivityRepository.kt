package com.yousefh.observeinternetconnection.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class ConnectivityRepository(private val context: Context) {

    private val _isConnected = MutableLiveData<Boolean>()

    val isConnected: LiveData<Boolean>
        get() = _isConnected

    fun registerNetworkCallback() {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                _isConnected.postValue(true)
            }

            override fun onLost(network: android.net.Network) {
                _isConnected.postValue(false)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            // For devices below Nougat, fallback to BroadcastReceiver method
            // You can implement BroadcastReceiver for pre-Nougat devices
        }
    }

    fun unregisterNetworkCallback() {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(ConnectivityManager.NetworkCallback())
    }

    init {
        // Initialize connectivity status
        _isConnected.postValue(isNetworkConnected())
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.isConnected ?: false
        }
    }
}
