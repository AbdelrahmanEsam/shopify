package com.example.shopify.auth.domain.usecases

import com.example.shopify.auth.domain.repository.AuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String) =
        authRepository.resetPassword(email = email)
}