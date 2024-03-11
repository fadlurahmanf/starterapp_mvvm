package com.fadlurahmanf.starterappmvvm.network.dto.response.guest_token

data class GenerateGuestTokenResponse(
    val accessToken: String? = null,
    val expiresIn: Int? = null
)
