package com.fadlurahmanf.starterappmvvm.example.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fadlurahmanf.starterappmvvm.core.shared.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.core.state.AppState
import com.fadlurahmanf.starterappmvvm.example.data.model.FirstLaunchModel
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCase
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val platformRepository: PlatformRepository,
    private val exampleUseCase: ExampleUseCase,
) : BaseViewModel() {

    private val _firstLaunchState = MutableLiveData<AppState<FirstLaunchModel>>()
    val firstLaunchState: LiveData<AppState<FirstLaunchModel>> = _firstLaunchState
    fun initializeFirstLaunch(context: Context) {
        _firstLaunchState.value = AppState.LOADING
        baseDisposable.add(exampleUseCase.initializeFirstLaunch(context)
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
}