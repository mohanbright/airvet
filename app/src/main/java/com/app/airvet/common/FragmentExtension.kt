package com.app.healios.ui.common

import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.app.airvet.ui.MainActivity
import com.app.airvet.common.BaseFragment

import com.google.android.material.tabs.TabLayout

fun createTabSelectedListener(tabSelectedBlock: (position: Int?) -> Unit): TabLayout.OnTabSelectedListener {
    return object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            tabSelectedBlock(tab?.position)
        }

    }
}

fun Fragment.navigateTo(directions: NavDirections) {
    try {
        findNavController().navigate(directions)

        this.removeMessageLoadingError() // Remove loading error message when user change.
    }
    catch (e: IllegalArgumentException) {
        Log.e("Fragment","Catching potential duplicate navigation event")
    }
}

// This is using only to remove loading error message when users travel between pages.
private fun Fragment.removeMessageLoadingError() {
    //(activity as? MainActivity)?.removeMessageLoadingError()
}

fun BaseFragment.actionBarCustom(view: View) {
    (activity as? MainActivity)?.setActionBarCustom(view)
}

fun Fragment.hideActionBar() {
    (activity as? MainActivity)?.hideActionBar()
}

fun Fragment.showActionBar() {
    (activity as? MainActivity)?.showActionBar()
}

fun Fragment.homeAsUpEnabled(enabled: Boolean) {
    (activity as? MainActivity)?.homeAsUpEnabled(enabled)
}

fun Fragment.getSupportBar(): ActionBar {
    return (activity as? MainActivity)?.getSupportBar()!!
}