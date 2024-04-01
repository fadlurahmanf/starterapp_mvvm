package com.fadlurahmanf.starterappmvvm.example.presentation.activity.worker

import android.os.Bundle
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkQuery
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.databinding.ActivityWorkerBinding
import com.fadlurahmanf.starterappmvvm.example.data.model.FeatureModel
import com.fadlurahmanf.starterappmvvm.example.others.workers.ExampleAPILargeContentWorker
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.utilities.recycle_view.ListExampleAdapter
import com.fadlurahmanf.starterappmvvm.core.constant.AppConstant
import java.util.UUID

class WorkerActivity : BaseExampleActivity<ActivityWorkerBinding>(ActivityWorkerBinding::inflate),
    ListExampleAdapter.Callback {
    private val features: List<FeatureModel> = listOf<FeatureModel>(
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Fake API POST With Large Content",
            desc = "Fake POST Large content using Work Manager",
            enum = "UPLOAD_API_LARGE_CONTENT"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Get latest info work manager",
            desc = "Get latest info work manager by tag",
            enum = "GET_INFO_LATEST_UPLOAD_API_LARGE_CONTENT"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Get list work manager",
            desc = "Get list work manager by unique name",
            enum = "GET_WORK_MANAGER_BY_UNIQUE_NAME"
        ),
    )

    override fun onBaseExampleInjectActivity() {
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
            "UPLOAD_API_LARGE_CONTENT" -> {
                doOneTimeRequest()
            }

            "GET_INFO_LATEST_UPLOAD_API_LARGE_CONTENT" -> {
                getInfo(uuid)
            }

            "GET_WORK_MANAGER_BY_UNIQUE_NAME" -> {
                getWorksByUniqueName("EXAMPLE_UPLOAD_LARGE_CONTENT")
            }
        }
    }

    private fun getWorksByUniqueName(uniqueName: String) {
        workManager.getWorkInfosForUniqueWorkLiveData(uniqueName)
            .observe(this) { works ->
                Log.d(AppConstant.LOGGER_TAG, "WORKERS LENGTH: ${works.size}")
                works.forEach { work ->
                    Log.d(AppConstant.LOGGER_TAG, "WORK ${work.id} ${work.state}")
                    val progress = work.progress.getInt("progress", -1)
                    Log.d(AppConstant.LOGGER_TAG, "PROGRESS WORKER: $progress")
                }
            }
    }

    private lateinit var oneTimeWorkRequest: OneTimeWorkRequest
    private val workManager: WorkManager by lazy {
        WorkManager.getInstance(this)
    }

    lateinit var uuid: UUID
    private fun doOneTimeRequest() {
        uuid = UUID.randomUUID()
        workManager.cancelAllWork()
        // oneTimeWorkRequest = OneTimeWorkRequest.from(ExampleUploadImageWorker::class.java)
        // or
        oneTimeWorkRequest = OneTimeWorkRequestBuilder<ExampleAPILargeContentWorker>()
            .setId(uuid)
            .build()
        workManager.enqueueUniqueWork(
            "EXAMPLE_UPLOAD_LARGE_CONTENT",
            ExistingWorkPolicy.REPLACE,
            oneTimeWorkRequest
        )
    }

    private fun getInfo(uuid: UUID) {
        workManager.getWorkInfoByIdLiveData(uuid)
            .observe(this) { work ->
                val progress = work.progress.getInt("progress", -1)
                Log.d(AppConstant.LOGGER_TAG, "PROGRESS WORKER: $progress")
                Log.d(AppConstant.LOGGER_TAG, "DATA: ${work.progress}")

                if (work.state == WorkInfo.State.SUCCEEDED) {
                    Log.d(AppConstant.LOGGER_TAG, "SUCCESS")
                } else if (work.state == WorkInfo.State.FAILED) {
                    Log.d(AppConstant.LOGGER_TAG, "FAILED")
                } else if (work.state == WorkInfo.State.CANCELLED) {
                    Log.d(AppConstant.LOGGER_TAG, "CANCELLED")
                }
            }
    }

}