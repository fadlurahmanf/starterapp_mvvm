package com.fadlurahmanf.starterappmvvm.example.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.databinding.ActivityListExampleBinding
import com.fadlurahmanf.starterappmvvm.example.data.model.FeatureModel
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.api_call.ApiCallActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.crashlytics.FirebaseCrashlyticsActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.crypto.AesCryptoActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.storage.StorageActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.worker.WorkerActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.utilities.recycle_view.ListExampleAdapter
import com.fadlurahmanf.starterappmvvm.example.presentation.ExampleViewModel
import com.fadlurahmanf.starterappmvvm.core.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.core.state.general.AppState
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification.ExampleNotificationActivity
import javax.inject.Inject

class ListExampleActivity :
    BaseExampleActivity<ActivityListExampleBinding>(ActivityListExampleBinding::inflate),
    ListExampleAdapter.Callback {

    @Inject
    lateinit var viewModel: ExampleViewModel

    private val features: List<FeatureModel> = listOf<FeatureModel>(
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Initialize First Install",
            desc = "Get existing appExamleEntity, if exist return the data, if not exist generate crypto key" +
                    " then save crypto key inside appExampleEntity and return the data",
            enum = "INITIALIZE_FIRST_INSTALL"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "API Call",
            desc = "List of how to fetch API",
            enum = "API_CALL"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Firebase Crashlytics",
            desc = "features of Firebase Crashlytics",
            enum = "FIREBASE_CRASHLYTICS"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Crypto",
            desc = "features of crypto",
            enum = "CRYPTO"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Storage",
            desc = "features of storage",
            enum = "STORAGE"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Worker Manager",
            desc = "features of Work Manager",
            enum = "WORK_MANAGER"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Notification",
            desc = "features of Notification",
            enum = "NOTIFICATION"
        ),
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

        viewModel.firstLaunchState.observe(this) {
            when (it) {
                is AppState.SUCCESS -> {
                    Log.d(AppConstant.LOGGER_TAG, "FIRST LAUNCH MODEL: ${it.data}")
                }

                else -> {}
            }
        }
    }

    override fun onClicked(item: FeatureModel) {
        when (item.enum) {
            "INITIALIZE_FIRST_INSTALL" -> {
                viewModel.initializeFirstLaunch(this)
            }

            "API_CALL" -> {
                val intent = Intent(this, ApiCallActivity::class.java)
                startActivity(intent)
            }

            "FIREBASE_CRASHLYTICS" -> {
                val intent = Intent(this, FirebaseCrashlyticsActivity::class.java)
                startActivity(intent)
            }

            "CRYPTO" -> {
                val intent = Intent(this, AesCryptoActivity::class.java)
                startActivity(intent)
            }

            "STORAGE" -> {
                val intent = Intent(this, StorageActivity::class.java)
                startActivity(intent)
            }

            "WORK_MANAGER" -> {
                val intent = Intent(this, WorkerActivity::class.java)
                startActivity(intent)
            }

            "NOTIFICATION" -> {
                val intent = Intent(this, ExampleNotificationActivity::class.java)
                startActivity(intent)
            }
        }
    }

}