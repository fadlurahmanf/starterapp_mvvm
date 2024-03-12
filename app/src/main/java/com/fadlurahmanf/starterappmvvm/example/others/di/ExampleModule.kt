package com.fadlurahmanf.starterappmvvm.example.others.di

import com.fadlurahmanf.starterappmvvm.BuildConfig
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSARepository
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleRepository
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleRepositoryImpl
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCases
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCasesFakeImpl
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCasesImpl
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppExampleLocalDatasource
import dagger.Module
import dagger.Provides

@Module(subcomponents = [ExampleSubComponent::class])
class ExampleModule {

    @Provides
    fun provideExampleRepository(appExampleLocalDatasource: AppExampleLocalDatasource): ExampleRepository {
        return ExampleRepositoryImpl(appExampleLocalDatasource)
    }

    @Provides
    fun provideExampleUseCases(
        exampleRepository: ExampleRepository,
        platformRepository: PlatformRepository,
        rsaRepository: CryptoRSARepository,
    ): ExampleUseCases {
        return when (BuildConfig.FLAVOR) {
            "fake" -> ExampleUseCasesFakeImpl(exampleRepository, platformRepository, rsaRepository)
            else -> ExampleUseCasesImpl(exampleRepository, platformRepository, rsaRepository)
        }
    }
}