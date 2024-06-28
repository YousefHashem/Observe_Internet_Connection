package com.yousefh.observeinternetconnection.ui

import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import android.content.Context
import android.view.View

class SnackBar(private val context: Context, private val rootView: View) {

    fun showSnackBar(message: String, backgroundColor: Int, textColor: Int) {
        val snackBar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
        snackBar.setTextColor(ContextCompat.getColor(context, textColor))
        snackBar.show()
    }
}
