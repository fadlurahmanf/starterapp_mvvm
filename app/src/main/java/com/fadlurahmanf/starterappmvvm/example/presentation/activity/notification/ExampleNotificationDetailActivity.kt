package com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification

import android.os.Bundle
import com.fadlurahmanf.starterappmvvm.databinding.ActivityExampleNotificationDetailBinding
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity

class ExampleNotificationDetailActivity :
    BaseExampleActivity<ActivityExampleNotificationDetailBinding>(
        ActivityExampleNotificationDetailBinding::inflate
    ) {
    override fun onBaseExampleInjectActivity() {}

    override fun onBaseExampleCreate(savedInstanceState: Bundle?) {
        setOnApplyWindowInsetsListener(binding.main)

        val debugText = intent.getStringExtra("DEBUG_TEXT")
        binding.tvText.text = intent.extras?.getString("DEBUG_TEXT", debugText ?: "-")
    }

}