package com.fadlurahmanf.starterappmvvm.example.presentation.activity

import android.os.Bundle
import com.fadlurahmanf.starterappmvvm.databinding.ActivityApiCallBinding
import com.fadlurahmanf.starterappmvvm.example.presentation.viewmodel.ApiCallViewModel
import javax.inject.Inject

class ApiCallActivity :
    BaseExampleActivity<ActivityApiCallBinding>(ActivityApiCallBinding::inflate) {

    @Inject
    lateinit var viewModel: ApiCallViewModel
    override fun onBaseExampleInjectActivity() {
        component.inject(this)
    }

    override fun onBaseExampleCreate(savedInstanceState: Bundle?) {
        setOnApplyWindowInsetsListener(binding.main)
    }
}