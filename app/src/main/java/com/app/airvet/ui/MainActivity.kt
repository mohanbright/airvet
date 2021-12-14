package com.app.airvet.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.app.airvet.R
import com.app.airvet.common.BaseActivity
import com.app.airvet.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainVM by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_content_main)
    }


    fun setActionBarCustom(view: View?) {
        val params = ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        view?.let { supportActionBar?.setCustomView(view, params) }
    }


    fun hideTitleBar() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun homeAsUpEnabled(enabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
    }

    fun getSupportBar(): ActionBar? {
        return supportActionBar
    }

    override fun onBackPressed() {

    }
}