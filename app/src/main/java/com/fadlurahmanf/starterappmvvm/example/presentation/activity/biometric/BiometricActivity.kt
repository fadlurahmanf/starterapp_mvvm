package com.fadlurahmanf.starterappmvvm.example.presentation.activity.biometric

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.core.state.AppState
import com.fadlurahmanf.starterappmvvm.databinding.ActivityBiometricBinding
import com.fadlurahmanf.starterappmvvm.example.data.model.FeatureModel
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.api_call.ApiCallActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.crashlytics.FirebaseCrashlyticsActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.crypto.AesCryptoActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.music_player.MusicPlayerActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification.ExampleNotificationActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.storage.StorageActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.worker.WorkerActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.utilities.recycle_view.ListExampleAdapter
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class BiometricActivity :
    BaseExampleActivity<ActivityBiometricBinding>(ActivityBiometricBinding::inflate),
    ListExampleAdapter.Callback {
    @Inject
    lateinit var viewModel: BiometricViewModel
    override fun onBaseExampleInjectActivity() {
        component.inject(this)
    }

    private val features: List<FeatureModel> = listOf<FeatureModel>(
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Biometric Permission",
            desc = "Check Is Device Permission For Biometric Granted",
            enum = "PERMISSION"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Supported Biometric",
            desc = "Check Is Device Supported Biometric",
            enum = "IS_SUPPORT_BIOMETRIC"
        ),
    )

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
            "PERMISSION" -> {
                viewModel.askBiometricPermission(
                    this,
                    onShouldShowRequestPermissionRationale = {
                        Log.d(AppConstant.LOGGER_TAG, "SHOULD SHOW REQUEST PERMISSION RATIONALE")
                    },
                    onCompleteCheckPermission = { isGranted, result ->
                        Log.d(
                            AppConstant.LOGGER_TAG,
                            "IS BIOMETRIC PERMISSION GRANTED: $isGranted, RESULT: $result"
                        )
                    },
                )
            }

            "IS_SUPPORT_BIOMETRIC" -> {
                Log.d(
                    AppConstant.LOGGER_TAG,
                    "IS DEVICE SUPPORTED BIOMETRIC: ${viewModel.isSupportedBiometric(this)}"
                )
            }
        }
    }

}