package com.app.airvet.common

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

abstract class BaseFragment : Fragment() {

    fun getThisActivity(): BaseActivity = requireActivity() as BaseActivity


    private val baseActivity: BaseActivity
        get() {
            val activity: FragmentActivity? = activity
            if (activity is BaseActivity) {
                return activity
            }
            throw RuntimeException("BaseActivity is null")
        }

     val isConnected: Boolean
        get() = baseActivity.isConnected

     fun showToast(message: String?) {
        val baseActivity = baseActivity
        baseActivity.showToast(message)
    }

     fun showNetworkSnackBar() {
        val baseActivity = baseActivity
        baseActivity.showNetworkSnackBar()
    }

     fun setActionBarMain() {
        val baseActivity = baseActivity
        baseActivity.setActionBarMain()
    }

     fun hideActionBar() {
        val baseActivity = baseActivity
        baseActivity.hideActionBar()
    }
}