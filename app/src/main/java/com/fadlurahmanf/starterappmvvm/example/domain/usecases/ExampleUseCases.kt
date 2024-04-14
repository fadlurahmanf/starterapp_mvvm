package com.fadlurahmanf.starterappmvvm.example.domain.usecases

import android.content.Context
import com.fadlurahmanf.starterappmvvm.example.data.model.FirstLaunchModel
import io.reactivex.rxjava3.core.Observable

interface ExampleUseCases {
    fun initializeFirstLaunch(context: Context): Observable<FirstLaunchModel>
    fun saveEncryptedString(key: String, plainText: String)
    fun getEncryptedString(key: String): String?
    fun getDecryptedString(key: String): String?
}