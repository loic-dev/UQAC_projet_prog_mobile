package com.example.projet_prog_mobile.domain.use_cases

import com.example.projet_prog_mobile.domain.model.RegisterInputValidationType
import javax.inject.Inject

class ValidateRegisterInputUseCase @Inject constructor() {
    operator fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        passwordInput: String,
        passwordConfirmInput: String,

    ): RegisterInputValidationType {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || passwordInput.isEmpty() || passwordConfirmInput.isEmpty()) {
            return RegisterInputValidationType.EmptyField
        }
        if ("@" !in email) {
            return RegisterInputValidationType.NoEmail
        }
        if (!passwordInput.equals(passwordConfirmInput)) {
            return RegisterInputValidationType.WrongPassword
        }
        return RegisterInputValidationType.Valid
    }
}