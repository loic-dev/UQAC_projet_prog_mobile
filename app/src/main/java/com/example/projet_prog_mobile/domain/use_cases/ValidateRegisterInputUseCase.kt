package com.example.projet_prog_mobile.domain.use_cases

import com.example.projet_prog_mobile.domain.model.RegisterInputValidationType
import javax.inject.Inject

class ValidateRegisterInputUseCase @Inject constructor() {
    operator fun invoke(email: String, password: String, password2: String): RegisterInputValidationType {
        if (email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            return RegisterInputValidationType.EmptyField
        }
        if ("@" !in email) {
            return RegisterInputValidationType.NoEmail
        }
        if (!password.equals(password2)) {
            return RegisterInputValidationType.WrongPassword
        }
        return RegisterInputValidationType.Valid
    }
}