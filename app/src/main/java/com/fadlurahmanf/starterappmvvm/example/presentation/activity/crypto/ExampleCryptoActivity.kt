package com.fadlurahmanf.starterappmvvm.example.presentation.activity.crypto

import android.os.Bundle
import android.util.Log
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.databinding.ActivityExampleCryptoBinding
import com.fadlurahmanf.starterappmvvm.example.data.model.FeatureModel
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.utilities.recycle_view.ListExampleAdapter
import com.github.fadlurahmanfdev.core_crypto.data.enums.AESMethod
import com.github.fadlurahmanfdev.core_crypto.data.enums.RSAMethod
import com.github.fadlurahmanfdev.core_crypto.data.enums.RSASignatureMethod
import com.github.fadlurahmanfdev.core_crypto.data.model.CryptoKey
import javax.inject.Inject

class ExampleCryptoActivity :
    BaseExampleActivity<ActivityExampleCryptoBinding>(ActivityExampleCryptoBinding::inflate),
    ListExampleAdapter.Callback {
    @Inject
    lateinit var viewModel: CryptoViewModel

    private val features: List<FeatureModel> = listOf<FeatureModel>(
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "AES KEY",
            desc = "Generate AES Key",
            enum = "GENERATE_AES_KEY"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Encrypt AES AES/CBC/ISO10126Padding",
            desc = "Encrypt AES with Modes CBC and Padding ISO10126Padding: ${AESMethod.AES_CBC_ISO10126Padding}",
            enum = "ENCRYPT_AES_CBC_ISO10126Padding"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Decrypt AES AES/CBC/ISO10126Padding",
            desc = "Decrypt AES with Modes CBC and Padding ISO10126Padding: ${AESMethod.AES_GCM_NoPadding}",
            enum = "DECRYPT_AES_CBC_ISO10126Padding"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Encrypt AES AES/GCM/NoPadding",
            desc = "Encrypt AES with Modes GCM and NoPadding: ${AESMethod.AES_GCM_NoPadding}",
            enum = "ENCRYPT_AES_GCM_NoPadding"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Decrypt AES AES/GCM/NoPadding",
            desc = "Decrypt AES with Modes GCM and NoPadding: ${AESMethod.AES_GCM_NoPadding}",
            enum = "DECRYPT_AES_GCM_NoPadding"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "ED25519 KEY",
            desc = "Generate ED25519 Key",
            enum = "GENERATE_ED25519_KEY"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "ED25519 Signature",
            desc = "Generate ED25519 Signature",
            enum = "GENERATE_ED25519_SIGNATURE"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Verify ED25519 Signature",
            desc = "Verify ED25519 Signature ",
            enum = "VERIFY_ED25519_SIGNATURE"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "RSA KEY",
            desc = "Generate RSA Key",
            enum = "GENERATE_RSA_KEY"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "RSA Signature",
            desc = "Generate RSA Signature",
            enum = "GENERATE_RSA_SIGNATURE"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Verify RSA Signature",
            desc = "Verify RSA Signature ",
            enum = "VERIFY_RSA_SIGNATURE"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Encrypt RSA",
            desc = "Encrypt RSA ",
            enum = "ENCRYPT_RSA"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Decrypt RSA",
            desc = "Decrypt RSA ",
            enum = "DECRYPT_RSA"
        ),
    )

    override fun onBaseExampleInjectActivity() {
        component.inject(this)
    }

    private lateinit var adapter: ListExampleAdapter
    override fun onBaseExampleCreate(savedInstanceState: Bundle?) {
        setOnApplyWindowInsetsListener(binding.main)
        binding.rv.setItemViewCacheSize(features.size)
        binding.rv.setHasFixedSize(true)

        adapter = ListExampleAdapter()
        adapter.setCallback(this)
        adapter.setList(features)
        adapter.setHasStableIds(true)
        binding.rv.adapter = adapter
    }

    lateinit var aesEncodedKey: String
    lateinit var aesEncryptedValue: String
    lateinit var ed25519Key: CryptoKey
    lateinit var ed25519Signature: String
    lateinit var rsaKey: CryptoKey
    lateinit var rsaSignature: String
    lateinit var rsaEncrypted: String
    override fun onClicked(item: FeatureModel) {
        when (item.enum) {
            "GENERATE_AES_KEY" -> {
                aesEncodedKey = viewModel.generateAESKey()
                Log.d(AppConstant.LOGGER_TAG, "AES KEY: $aesEncodedKey")
            }

            "ENCRYPT_AES_CBC_ISO10126Padding" -> {
                val result = viewModel.encryptAES(
                    aesEncodedKey,
                    "TES_VALUE_ENCRYPT_AES",
                    AESMethod.AES_CBC_ISO10126Padding
                )
                Log.d(AppConstant.LOGGER_TAG, "ENCRYPTED VALUE AES/CBC/ISO10126Padding: $result")
                if (result != null) {
                    aesEncryptedValue = result
                }
            }

            "DECRYPT_AES_CBC_ISO10126Padding" -> {
                val result = viewModel.decryptAES(
                    aesEncodedKey,
                    aesEncryptedValue,
                    AESMethod.AES_CBC_ISO10126Padding
                )
                Log.d(AppConstant.LOGGER_TAG, "DECRYPTED VALUE AES/CBC/ISO10126Padding: $result")
            }

            "ENCRYPT_AES_GCM_NoPadding" -> {
                val result = viewModel.encryptAES(
                    aesEncodedKey,
                    "TES_VALUE_ENCRYPT_AES",
                    AESMethod.AES_GCM_NoPadding
                )
                Log.d(AppConstant.LOGGER_TAG, "ENCRYPTED VALUE AES/GCM/NoPadding: $result")
                if (result != null) {
                    aesEncryptedValue = result
                }
            }

            "DECRYPT_AES_GCM_NoPadding" -> {
                val result = viewModel.decryptAES(
                    aesEncodedKey,
                    aesEncryptedValue,
                    AESMethod.AES_GCM_NoPadding
                )
                Log.d(AppConstant.LOGGER_TAG, "DECRYPTED VALUE AES/GCM/NoPadding: $result")
            }

            "GENERATE_ED25519_KEY" -> {
                ed25519Key = viewModel.generateEd25119Key()
                Log.d(
                    AppConstant.LOGGER_TAG, "ED25519 PUBLIC ENCODED: ${ed25519Key.privateKey}" +
                            "\n\n-------------\n\n" +
                            "ED25519 PUBLIC KEY ENCODED: ${ed25519Key.publicKey}"
                )
            }

            "GENERATE_ED25519_SIGNATURE" -> {
                val signature = viewModel.generateEd25119Signature(
                    encodedPrivateKey = ed25519Key.privateKey,
                    plainText = "TES_SIGNATURE_ED25519"
                )
                Log.d(AppConstant.LOGGER_TAG, "ED25519 Signature: $signature")
                if (signature != null) {
                    ed25519Signature = signature
                }
            }

            "VERIFY_ED25519_SIGNATURE" -> {
                Log.d(
                    AppConstant.LOGGER_TAG, "VERIFY ED25519 SIGNATURE: ${
                        viewModel.verifyEd25119Signature(
                            encodedPublicKey = ed25519Key.publicKey,
                            plainText = "TES_SIGNATURE_ED25519",
                            signature = ed25519Signature,
                        )
                    }"
                )
            }

            "GENERATE_RSA_KEY" -> {
                rsaKey = viewModel.generateRSAKey()
                Log.d(
                    AppConstant.LOGGER_TAG, "RSA PUBLIC ENCODED: ${rsaKey.privateKey}" +
                            "\n\n-------------\n\n" +
                            "RSA PUBLIC KEY ENCODED: ${rsaKey.publicKey}"
                )
            }

            "GENERATE_RSA_SIGNATURE" -> {
                val signature = viewModel.generateRSASignature(
                    encodedPrivateKey = rsaKey.privateKey,
                    plainText = "TES_SIGNATURE_RSA",
                    method = RSASignatureMethod.SHA1withRSA,
                )
                Log.d(AppConstant.LOGGER_TAG, "RSA Signature: $signature")
                if (signature != null) {
                    rsaSignature = signature
                }
            }

            "VERIFY_RSA_SIGNATURE" -> {
                Log.d(
                    AppConstant.LOGGER_TAG, "VERIFY RSA SIGNATURE: ${
                        viewModel.verifyRSASignature(
                            encodedPublicKey = rsaKey.publicKey,
                            plainText = "TES_SIGNATURE_RSA",
                            signature = rsaSignature,
                            method = RSASignatureMethod.SHA1withRSA,
                        )
                    }"
                )
            }

            "ENCRYPT_RSA" -> {
                val result = viewModel.encryptRSA(
                    publicKey = rsaKey.publicKey,
                    "Test Test RSA",
                    RSAMethod.RSA_ECB_OAEPPadding
                )
                Log.d(AppConstant.LOGGER_TAG, "encrypted RSA: $result")
                if (result != null) {
                    rsaEncrypted = result
                }
            }

            "DECRYPT_RSA" -> {
                val result = viewModel.decryptRSA(
                    privateKey = rsaKey.privateKey,
                    rsaEncrypted,
                    RSAMethod.RSA_ECB_OAEPPadding
                )
                Log.d(AppConstant.LOGGER_TAG, "decrypted RSA: $result")
            }
        }
    }

}