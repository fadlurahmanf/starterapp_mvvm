package com.fadlurahmanf.starterappmvvm.example.domain.usecases

import android.content.Context
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSARepository
import com.fadlurahmanf.starterappmvvm.example.data.model.FirstLaunchModel
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleStorageRepository
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppExampleEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

open class ExampleUseCasesImpl @Inject constructor(
    private val exampleStorageRepository: ExampleStorageRepository,
    private val platformRepository: PlatformRepository,
    private val rsaRepository: CryptoRSARepository,
) : ExampleUseCases {

    override fun initializeFirstLaunch(context: Context): Observable<FirstLaunchModel> {
        return exampleStorageRepository.getExistingAppExampleEntity().toObservable()
            .map { entities ->
                val deviceId = platformRepository.getDeviceId(context)
                var isFirstInstall = false
                val publicKey: String
                val privateKey: String
                if (entities.isEmpty()) {
                    isFirstInstall = true
                    val cryptoKey = rsaRepository.generateKey()
                    publicKey = cryptoKey.publicKey
                    privateKey = cryptoKey.privateKey
                    val entity = AppExampleEntity(
                        deviceId = deviceId,
                        encodedPublicKey = cryptoKey.publicKey,
                        encodedPrivateKey = cryptoKey.privateKey,
                    )
                    exampleStorageRepository.saveAppExampleEntity(entity)
                } else {
                    val entity = entities.first()
                    publicKey = entity.encodedPublicKey
                    privateKey = entity.encodedPrivateKey
                }
                FirstLaunchModel(
                    deviceId = deviceId,
                    publicKey = publicKey,
                    privateKey = privateKey,
                    isFirstInstall = isFirstInstall
                )
            }
    }

    override fun saveEncryptedString(key: String, plainText: String) =
        exampleStorageRepository.saveEncryptedString(key, plainText)

    override fun getEncryptedString(key: String): String? =
        exampleStorageRepository.getString(key)

    override fun getDecryptedString(key: String): String? =
        exampleStorageRepository.getDecryptedString(key, null)
}