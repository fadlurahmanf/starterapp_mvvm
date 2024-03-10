package com.fadlurahmanf.starterappmvvm.example.presentation.activity.api_call

import android.os.Bundle
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.databinding.ActivityApiCallBinding
import com.fadlurahmanf.starterappmvvm.example.data.model.FeatureModel
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.utilities.recycle_view.ListExampleAdapter
import com.fadlurahmanf.starterappmvvm.example.presentation.viewmodel.ApiCallViewModel
import javax.inject.Inject

class ApiCallActivity :
    BaseExampleActivity<ActivityApiCallBinding>(ActivityApiCallBinding::inflate),
    ListExampleAdapter.Callback {

    @Inject
    lateinit var viewModel: ApiCallViewModel
    private val features: List<FeatureModel> = listOf<FeatureModel>(
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "API Generate Guest Token",
            desc = "Generate Guest Token",
            enum = "GENERATE_GUEST_TOKEN"
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
            "GENERATE_GUEST_TOKEN" -> {

            }
        }
    }
}