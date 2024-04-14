package com.fadlurahmanf.starterappmvvm.example.presentation.activity.storage

import com.fadlurahmanf.starterappmvvm.core.shared.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleUseCases
import javax.inject.Inject

class ExampleStorageViewModel @Inject constructor(
    private val exampleUseCases: ExampleUseCases
) : BaseViewModel() {

    fun saveExampleEncryptedString(value: String) =
        exampleUseCases.saveEncryptedString(AppConstant.EXAMPLE_ENCRYPTED_STRING_KEY, value)

    fun getExampleEncryptedString() =
        exampleUseCases.getEncryptedString(AppConstant.EXAMPLE_ENCRYPTED_STRING_KEY)

    fun getExampleDecryptedString() =
        exampleUseCases.getDecryptedString(AppConstant.EXAMPLE_ENCRYPTED_STRING_KEY)
}