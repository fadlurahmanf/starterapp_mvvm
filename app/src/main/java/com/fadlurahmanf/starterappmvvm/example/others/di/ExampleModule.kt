package com.fadlurahmanf.starterappmvvm.example.others.di

import com.fadlurahmanf.starterappmvvm.BuildConfig
import com.fadlurahmanf.starterappmvvm.core.notification.data.repositories.NotificationRepository
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleNotificationRepository
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleNotificationRepositoryImpl
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleStorageRepository
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleStorageRepositoryImpl
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCase
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCaseFakeImpl
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCaseImpl
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppExampleLocalDatasource
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppSharedPrefLocalDatasource
import com.github.fadlurahmanfdev.core_crypto.data.repositories.CryptoRSARepository
import dagger.Module
import dagger.Provides

@Module(subcomponents = [ExampleSubComponent::class])
class ExampleModule {

    @Provides
    fun provideExampleRepository(
        appExampleLocalDatasource: AppExampleLocalDatasource,
        appSharedPrefLocalDatasource: AppSharedPrefLocalDatasource
    ): ExampleStorageRepository {
        return ExampleStorageRepositoryImpl(appExampleLocalDatasource, appSharedPrefLocalDatasource)
    }

    @Provides
    fun provideExampleNotificationRepository(notificationRepository: NotificationRepository): ExampleNotificationRepository {
        return ExampleNotificationRepositoryImpl(notificationRepository)
    }

    @Provides
    fun provideExampleUseCases(
        exampleNotificationRepository: ExampleNotificationRepository,
        exampleStorageRepository: ExampleStorageRepository,
        platformRepository: PlatformRepository,
        cryptoRSARepository: CryptoRSARepository,
    ): ExampleUseCase {
        return when (BuildConfig.FLAVOR) {
            "fake" -> ExampleUseCaseFakeImpl(
                exampleNotificationRepository,
                exampleStorageRepository,
                platformRepository,
                cryptoRSARepository
            )

            else -> ExampleUseCaseImpl(
                exampleNotificationRepository,
                exampleStorageRepository,
                platformRepository,
                cryptoRSARepository,
            )
        }
    }
}