package com.fadlurahmanf.starterappmvvm.example.domain.usecases

import android.app.Activity
import android.content.Context
import com.fadlurahmanf.starterappmvvm.example.data.model.FirstLaunchModel
import io.reactivex.rxjava3.core.Observable

interface ExampleUseCase {
    fun initializeFirstLaunch(context: Context): Observable<FirstLaunchModel>
    fun saveEncryptedString(key: String, plainText: String)
    fun getEncryptedString(key: String): String?
    fun getDecryptedString(key: String): String?
    fun askNotificationPermission(
        activity: Activity,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onCompleteCheckPermission: (isGranted: Boolean, result: Int) -> Unit,
    )
}