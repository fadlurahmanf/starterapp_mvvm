package com.fadlurahmanf.starterappmvvm.example.others.di

import com.fadlurahmanf.starterappmvvm.BuildConfig
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSARepository
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleStorageRepository
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleStorageRepositoryImpl
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCases
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCasesFakeImpl
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCasesImpl
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppExampleLocalDatasource
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppSharedPrefLocalDatasource
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
    fun provideExampleUseCases(
        exampleStorageRepository: ExampleStorageRepository,
        platformRepository: PlatformRepository,
        rsaRepository: CryptoRSARepository,
    ): ExampleUseCases {
        return when (BuildConfig.FLAVOR) {
            "fake" -> ExampleUseCasesFakeImpl(
                exampleStorageRepository,
                platformRepository,
                rsaRepository
            )

            else -> ExampleUseCasesImpl(exampleStorageRepository, platformRepository, rsaRepository)
        }
    }
}