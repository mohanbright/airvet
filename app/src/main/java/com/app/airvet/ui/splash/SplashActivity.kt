package com.app.airvet.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.app.airvet.ui.MainActivity
import com.app.airvet.common.BaseActivity

class SplashActivity : BaseActivity() {

    private val delayDuration: Long = 3000
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create delay for 3000 ms.
        createDelay()
    }

    private fun createDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            // Directly open Main Activity.
            openMainActivity()
        }, delayDuration)
    }

    private fun openMainActivity() {
        // Start main activity.
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        // Close activity.
        finish()
    }
}