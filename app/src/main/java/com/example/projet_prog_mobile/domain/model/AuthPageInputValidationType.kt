package com.example.projet_prog_mobile.domain.model

enum class EmailInputValidationType {
    EmptyField,
    NoEmail,
    Valid
}

enum class TextInputValidationType {
    EmptyField,
    Valid
}

enum class PasswordInputValidationType {
    EmptyField,
    NoPassword,
    Valid
}

enum class PasswordConfirmInputValidationType {
    EmptyField,
    WrongPassword,
    Valid
}