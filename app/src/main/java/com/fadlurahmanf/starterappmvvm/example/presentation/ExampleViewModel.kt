package com.fadlurahmanf.starterappmvvm.example.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoAESRepository
import com.fadlurahmanf.starterappmvvm.example.data.model.FirstLaunchModel
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCases
import com.fadlurahmanf.starterappmvvm.core.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.core.state.general.AppState
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val platformRepository: PlatformRepository,
    private val aesCryptoRepository: CryptoAESRepository,
    private val exampleUseCases: ExampleUseCases,
) : BaseViewModel() {

    private val _firstLaunchState = MutableLiveData<AppState<FirstLaunchModel>>()
    val firstLaunchState: LiveData<AppState<FirstLaunchModel>> = _firstLaunchState
    fun initializeFirstLaunch(context: Context) {
        _firstLaunchState.value = AppState.LOADING
        baseDisposable.add(exampleUseCases.initializeFirstLaunch(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _firstLaunchState.value = AppState.SUCCESS(it)
                },
                {
//                    _firstLaunchState.value = AppState.FAILED()
                },
                {}
            ))
    }

    fun getUserId(context: Context): String = platformRepository.getDeviceId(context)

    fun generateKey(): String = aesCryptoRepository.generateKey()

    fun encryptAESECB(text: String, key: String): String? {
        return aesCryptoRepository.encryptECB(
            plainText = text,
            secretKey = key
        )
    }

    fun encryptAESCCB(text: String, key: String): String? {
        return aesCryptoRepository.encryptCBC(
            plainText = text,
            secretKey = key
        )
    }

    fun decryptAESECB(text: String, key: String): String? {
        return aesCryptoRepository.decryptECB(
            encryptedText = text,
            secretKey = key
        )
    }

    fun decryptAESCCB(text: String, key: String): String? {
        return aesCryptoRepository.decryptCBC(
            encryptedText = text,
            secretKey = key
        )
    }
}