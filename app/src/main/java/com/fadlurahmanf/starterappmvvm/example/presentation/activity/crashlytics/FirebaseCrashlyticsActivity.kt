package com.fadlurahmanf.starterappmvvm.example.presentation.activity.crashlytics

import android.os.Bundle
import android.util.Log
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.databinding.ActivityFirebaseCrashlyticsBinding
import com.fadlurahmanf.starterappmvvm.example.data.model.FeatureModel
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.utilities.recycle_view.ListExampleAdapter
import com.fadlurahmanf.starterappmvvm.example.presentation.viewmodel.ExampleViewModel
import javax.inject.Inject

class FirebaseCrashlyticsActivity :
    BaseExampleActivity<ActivityFirebaseCrashlyticsBinding>(ActivityFirebaseCrashlyticsBinding::inflate),
    ListExampleAdapter.Callback {

    @Inject
    lateinit var viewModel: ExampleViewModel

    private val features: List<FeatureModel> = listOf<FeatureModel>(
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Test Crashlytics",
            desc = "Force Crash to test Firebase Crashlytics",
            enum = "FORCE_CRASH_CRASHLYTICS"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Set Firebase Crashlytics ID",
            desc = "Set Firebase Crashlytics ID",
            enum = "SET_FIREBASE_CRASHLYTICS_ID"
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
            "FORCE_CRASH_CRASHLYTICS" -> {
                throw RuntimeException("Test Crash Firebase Crashlytics")
            }

            "SET_FIREBASE_CRASHLYTICS_ID" -> {
                val deviceId = viewModel.getUserId(this)
                Log.d("BaseLoggerTAG", "DEVICE ID: $deviceId")
                firebaseCrashlytics.setUserId(deviceId)
            }
        }
    }

}