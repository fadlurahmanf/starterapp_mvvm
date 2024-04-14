package com.fadlurahmanf.starterappmvvm.example.domain.usecases

import android.content.Context
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSARepository
import com.fadlurahmanf.starterappmvvm.example.data.model.FirstLaunchModel
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleStorageRepository
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ExampleUseCasesFakeImpl @Inject constructor(
    private val exampleStorageRepository: ExampleStorageRepository,
    private val platformRepository: PlatformRepository,
    private val rsaRepository: CryptoRSARepository,
) : ExampleUseCasesImpl(exampleStorageRepository, platformRepository, rsaRepository) {

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