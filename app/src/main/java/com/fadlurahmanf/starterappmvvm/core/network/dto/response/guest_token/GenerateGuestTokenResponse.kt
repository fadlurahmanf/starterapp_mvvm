package com.fadlurahmanf.starterappmvvm.core.network.dto.response.guest_token

data class GenerateGuestTokenResponse(
    val accessToken: String? = null,
    val expiresIn: Int? = null
)
