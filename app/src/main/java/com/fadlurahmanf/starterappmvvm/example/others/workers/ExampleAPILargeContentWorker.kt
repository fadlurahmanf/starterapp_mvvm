package com.fadlurahmanf.starterappmvvm.example.others.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.fadlurahmanf.starterappmvvm.others.constant.AppConstant
import kotlinx.coroutines.delay

// Example upload image using work manager
// usually upload image need a large or amount of time to reach a server
// so we want user no need to wait a loading
class ExampleAPILargeContentWorker(
    context: Context, params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        Log.d(AppConstant.LOGGER_TAG, "PROGRESS 0")
        setProgress(workDataOf("progress" to 0))
        delay(5000)
        setProgress(workDataOf("progress" to 5))
        delay(5000)
        setProgress(workDataOf("progress" to 10))
        delay(5000)
        setProgress(workDataOf("progress" to 15))
        delay(5000)
        setProgress(workDataOf("progress" to 20))
        delay(5000)
        setProgress(workDataOf("progress" to 25))
        delay(5000)
        Log.d(AppConstant.LOGGER_TAG, "PROGRESS 30")
        return Result.success()
    }
}