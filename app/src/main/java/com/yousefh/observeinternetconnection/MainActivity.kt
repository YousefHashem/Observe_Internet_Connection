package com.yousefh.observeinternetconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.yousefh.observeinternetconnection.repository.ConnectivityRepository
import com.yousefh.observeinternetconnection.ui.SnackBar
import com.yousefh.observeinternetconnection.viewmodel.ObserveInternetViewModel
import com.yousefh.observeinternetconnection.viewmodel.ObserveInternetViewModelFactory

class MainActivity : AppCompatActivity() {

    // Observe Internet Connectivity
    private lateinit var observeInternetViewModel: ObserveInternetViewModel
    private var internetLost: Int = 0

    // SnackBar View Class
    private lateinit var snackBar: SnackBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeInternet()

    } // onCreate End

    private fun observeInternet() {
        // Initialize ViewModel
        val connectivityRepository = ConnectivityRepository(applicationContext)
        val viewModelFactory = ObserveInternetViewModelFactory(connectivityRepository)
        observeInternetViewModel = ViewModelProvider(this, viewModelFactory)[ObserveInternetViewModel::class.java]

        // Observe connectivity status
        observeInternetViewModel.isConnected.observe(this) { isConnected ->
            // Update UI based on connectivity status
            if (isConnected && internetLost > 0) snackBar.showSnackBar("Internet connection has been restored", R.color.green, R.color.black) // Connected
            else if (!isConnected){
                // Disconnected
                internetLost++
                snackBar.showSnackBar("Lost internet connection!", R.color.red, R.color.white)
            }
        }
    } // observeInternet End

    override fun onDestroy() {
        super.onDestroy()
        observeInternetViewModel.onDestroy()
    }

}