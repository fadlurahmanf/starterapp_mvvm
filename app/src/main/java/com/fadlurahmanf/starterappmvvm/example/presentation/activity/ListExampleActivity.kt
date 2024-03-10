package com.fadlurahmanf.starterappmvvm.example.presentation.activity

import android.content.Intent
import android.os.Bundle
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.databinding.ActivityListExampleBinding
import com.fadlurahmanf.starterappmvvm.example.data.model.FeatureModel
import com.fadlurahmanf.starterappmvvm.example.presentation.utilities.recycle_view.ListExampleAdapter

class ListExampleActivity :
    BaseExampleActivity<ActivityListExampleBinding>(ActivityListExampleBinding::inflate),
    ListExampleAdapter.Callback {

    private val features: List<FeatureModel> = listOf<FeatureModel>(
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "API Call",
            desc = "List of how to fetch API",
            enum = "API_CALL"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Test Crashlytics",
            desc = "Force Crash to test Firebase Crashlytics",
            enum = "FORCE_CRASH_CRASHLYTICS"
        )
    )

    override fun onBaseExampleInjectActivity() {
        component.inject(this)
    }

    private lateinit var adapter: ListExampleAdapter
    override fun onBaseExampleCreate(savedInstanceState: Bundle?) {
        setOnApplyWindowInsetsListener(binding.main)
        binding.rv.setItemViewCacheSize(features.size)
        binding.rv.setHasFixedSize(true)

        adapter = ListExampleAdapter()
        adapter.setCallback(this)
        adapter.setList(features)
        adapter.setHasStableIds(true)
        binding.rv.adapter = adapter
    }

    override fun onClicked(item: FeatureModel) {
        when (item.enum) {
            "API_CALL" -> {
                val intent = Intent(this, ApiCallActivity::class.java)
                startActivity(intent)
            }

            "FORCE_CRASH_CRASHLYTICS" -> {
                throw RuntimeException("Test Crash Firebase Crashlytics")
            }
        }
    }

}