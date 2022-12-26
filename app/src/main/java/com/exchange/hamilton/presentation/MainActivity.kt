package com.exchange.hamilton.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import com.exchange.hamilton.R
import com.exchange.hamilton.databinding.ActivityMainBinding
import com.exchange.hamilton.presentation.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)
        val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount
        if(backStackEntryCount ==0){
            confirmExit()
        }else{
            super.onBackPressed()
        }
    }

    private fun confirmExit() {

    }
}