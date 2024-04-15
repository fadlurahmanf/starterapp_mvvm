package com.fadlurahmanf.starterappmvvm.example.presentation.activity.storage

import com.fadlurahmanf.starterappmvvm.core.shared.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCase
import javax.inject.Inject

class ExampleStorageViewModel @Inject constructor(
    private val exampleUseCase: ExampleUseCase
) : BaseViewModel() {

    fun saveExampleEncryptedString(value: String) =
        exampleUseCase.saveEncryptedString(AppConstant.EXAMPLE_ENCRYPTED_STRING_KEY, value)

    fun getExampleEncryptedString() =
        exampleUseCase.getEncryptedString(AppConstant.EXAMPLE_ENCRYPTED_STRING_KEY)

    fun getExampleDecryptedString() =
        exampleUseCase.getDecryptedString(AppConstant.EXAMPLE_ENCRYPTED_STRING_KEY)
}