package com.fadlurahmanf.starterappmvvm.feature_api_call.presentation.activity

import android.os.Bundle
import com.fadlurahmanf.starterappmvvm.databinding.ActivityMainBinding
import com.fadlurahmanf.starterappmvvm.others.layout.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onBaseCreate(savedInstanceState: Bundle?) {
        setOnApplyWindowInsetsListener(binding.main)
    }
}