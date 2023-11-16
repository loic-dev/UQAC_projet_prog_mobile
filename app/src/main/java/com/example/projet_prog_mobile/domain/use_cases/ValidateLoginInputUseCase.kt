package com.example.projet_prog_mobile.domain.use_cases

import com.example.projet_prog_mobile.domain.model.EmailInputValidationType
import com.example.projet_prog_mobile.domain.model.PasswordInputValidationType


class ValidateEmailInputUseCase {
    operator fun invoke(input:String): EmailInputValidationType {
        if(input.isEmpty()){
            return EmailInputValidationType.EmptyField
        }
        if(input == "test"){
            return EmailInputValidationType.NoEmail
        }
        return EmailInputValidationType.Valid
    }
}

class ValidatePasswordInputUseCase {
    operator fun invoke(input:String):PasswordInputValidationType{
        if(input.isEmpty()){
            return PasswordInputValidationType.EmptyField
        }
        return PasswordInputValidationType.Valid
    }
}