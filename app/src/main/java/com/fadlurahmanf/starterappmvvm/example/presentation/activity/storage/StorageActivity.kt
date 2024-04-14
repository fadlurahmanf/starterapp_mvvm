package com.fadlurahmanf.starterappmvvm.example.presentation.activity.storage

import android.os.Bundle
import android.util.Log
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.databinding.ActivityStorageBinding
import com.fadlurahmanf.starterappmvvm.example.data.model.FeatureModel
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.utilities.recycle_view.ListExampleAdapter
import javax.inject.Inject

class StorageActivity :
    BaseExampleActivity<ActivityStorageBinding>(ActivityStorageBinding::inflate),
    ListExampleAdapter.Callback {

    private val features: List<FeatureModel> = listOf<FeatureModel>(
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Save Encrypted String",
            desc = "Save Plain Text to Encrypted String",
            enum = "SAVE_ENCRYPTED_STRING"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Get Encrypted String",
            desc = "Get Encrypted String without decrypted",
            enum = "GET_ENCRYPTED_STRING"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Get Decrypted String",
            desc = "Get Decrypted String form saved Encrypted String",
            enum = "GET_DECRYPTED_STRING"
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
    }

    @Inject
    lateinit var viewModel: ExampleStorageViewModel

    override fun onClicked(item: FeatureModel) {
        when (item.enum) {
            "SAVE_ENCRYPTED_STRING" -> {
                viewModel.saveExampleEncryptedString("VALUE_EXAMPLE_ENCRYPTED")
            }

            "GET_ENCRYPTED_STRING" -> {
                val savedString = viewModel.getExampleEncryptedString()
                Log.d(AppConstant.LOGGER_TAG, "Saved String: $savedString")
            }

            "GET_DECRYPTED_STRING" -> {
                Log.d(
                    AppConstant.LOGGER_TAG,
                    "Decrypted String: ${viewModel.getExampleDecryptedString()}"
                )
            }
        }
    }

}