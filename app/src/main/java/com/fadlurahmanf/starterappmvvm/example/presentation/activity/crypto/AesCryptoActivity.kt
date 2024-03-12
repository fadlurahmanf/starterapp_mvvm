package com.fadlurahmanf.starterappmvvm.example.presentation.activity.crypto

import android.annotation.SuppressLint
import android.os.Bundle
import com.fadlurahmanf.starterappmvvm.databinding.ActivityCryptoBinding
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.viewmodel.ExampleViewModel
import javax.inject.Inject

class AesCryptoActivity :
    BaseExampleActivity<ActivityCryptoBinding>(ActivityCryptoBinding::inflate) {

    @Inject
    lateinit var viewModel: ExampleViewModel

    private var inputText = "Hello to crypto Activity, Taufik!"

    override fun onBaseExampleInjectActivity() {
        component.inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onBaseExampleCreate(savedInstanceState: Bundle?) {
        setOnApplyWindowInsetsListener(binding.main)
        val key = viewModel.generateKey()
        binding.tvGeneratedKey.text = "KEY: $key"
        binding.tvInputText.text = "TEXT: $inputText"

        val encryptedECB = viewModel.encryptAESECB(inputText, key)
        val encryptedCCB = viewModel.encryptAESCCB(inputText, key)
        binding.tvEncryptedText.text = "ENCRYPTED ECB: $encryptedECB" +
                "\n" +
                "ENCRYPTED CBC: $encryptedCCB"

        if (encryptedECB != null && encryptedCCB != null) {
            binding.tvDecryptedText.text =
                "DECRYPTED ECB: ${viewModel.decryptAESECB(encryptedECB, key)}" +
                        "\n" +
                        "DECRYPTED CBC: ${viewModel.decryptAESCCB(encryptedCCB, key)}"
        }
    }

}