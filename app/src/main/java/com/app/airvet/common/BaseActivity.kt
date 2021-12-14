package com.app.airvet.common

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.StrictMode
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.airvet.R
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    private var fragment: Fragment? = null
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        //supportFragmentManager.addOnBackStackChangedListener(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 1) {
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        return try {
            if (event.action == MotionEvent.ACTION_DOWN) {
                val v = currentFocus
                if (v is EditText) {
                    val outRect = Rect()
                    v.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                        v.clearFocus()
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                    }
                }
            }
            super.dispatchTouchEvent(event)
        } catch (ignored: Exception) {
            false
        }
    }

    private fun shouldDisplayHomeUp() {
        val canback = supportFragmentManager.backStackEntryCount > 1
        Objects.requireNonNull(supportActionBar)!!.setDisplayHomeAsUpEnabled(canback)
    }

    fun setActionBarMain() {
        showActionBar()
        supportActionBar?.setCustomView(R.layout.topbar_layout_details)
        shouldDisplayHomeUp()
    }

    fun hideActionBar() {
        supportActionBar?.hide()
    }
    fun showActionBar() {
        supportActionBar?.hide()
    }
    fun showToast(message: String?) {}
    fun showNetworkSnackBar() {}
    val isConnected: Boolean
        get() = false

    companion object {
        private val TAG = BaseActivity::class.java.simpleName
        private const val BACK_STACK_ROOT_TAG = "ROOT_FRAGMENT"
    }

}