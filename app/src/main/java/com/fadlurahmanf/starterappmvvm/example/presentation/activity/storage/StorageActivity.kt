package com.fadlurahmanf.starterappmvvm.example.presentation.activity.storage

import android.os.Bundle
import com.fadlurahmanf.starterappmvvm.databinding.ActivityStorageBinding
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity

class StorageActivity :
    BaseExampleActivity<ActivityStorageBinding>(ActivityStorageBinding::inflate) {
    override fun onBaseExampleInjectActivity() {
        component.inject(this)
    }

    override fun onBaseExampleCreate(savedInstanceState: Bundle?) {
        setOnApplyWindowInsetsListener(binding.main)
    }

}