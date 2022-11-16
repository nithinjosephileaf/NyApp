package com.nithi.nyapp.ui

import ResponseState
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nithi.nyapp.R
import com.nithi.nyapp.databinding.ActivityMainBinding
import com.nithi.nyapp.ui.viewmodel.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewmodel>()
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getNews()

        lifecycleScope.launch{
            repeatOnLifecycle(state = Lifecycle.State.STARTED){

            }
        }

    }
    fun showLoader(flag: Boolean) {
        if (flag) {
            binding.clLoader.visibility = View.VISIBLE

        } else {
            binding.clLoader.visibility = View.GONE

        }
    }
}