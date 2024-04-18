package com.fadlurahmanf.starterappmvvm.example.domain.usecases

import android.content.Context
import com.fadlurahmanf.starterappmvvm.example.data.model.FirstLaunchModel
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleNotificationRepository
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleStorageRepository
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import com.github.fadlurahmanfdev.core_crypto.data.repositories.CryptoRSARepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ExampleUseCaseFakeImpl @Inject constructor(
    exampleNotificationRepository: ExampleNotificationRepository,
    exampleStorageRepository: ExampleStorageRepository,
    private val platformRepository: PlatformRepository,
    cryptoRSARepository: CryptoRSARepository,
) : ExampleUseCaseImpl(
    exampleNotificationRepository,
    exampleStorageRepository,
    platformRepository,
    cryptoRSARepository
) {

    override fun initializeFirstLaunch(context: Context): Observable<FirstLaunchModel> {
        return Observable.create {
            val deviceId = platformRepository.getDeviceId(context)
            it.onNext(
                FirstLaunchModel(
                    deviceId = deviceId,
                    publicKey = "FAKE_PUBLIC_KEY",
                    privateKey = "FAKE_PRIVATE_KEY",
                    isFirstInstall = true,
                )
            )
            it.onComplete()
        }
    }
}