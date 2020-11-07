package com.monofire.appcentchallange.event

import android.content.Context
import android.view.View
import androidx.navigation.fragment.NavHostFragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.snackbar.Snackbar

fun Context.snackbar(requireView: View, message: String) {
    Snackbar.make(requireView, message, Snackbar.LENGTH_LONG).show()
}

