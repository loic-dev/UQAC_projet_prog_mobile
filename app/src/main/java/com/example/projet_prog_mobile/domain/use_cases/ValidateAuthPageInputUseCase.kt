package com.example.projet_prog_mobile.domain.use_cases

import com.example.projet_prog_mobile.domain.model.EmailInputValidationType
import com.example.projet_prog_mobile.domain.model.PasswordConfirmInputValidationType
import com.example.projet_prog_mobile.domain.model.PasswordInputValidationType
import com.example.projet_prog_mobile.domain.model.TextInputValidationType
import javax.inject.Inject


class ValidateAuthInputTextUseCase @Inject constructor(){
    operator fun invoke(input:String): TextInputValidationType {
        if(input.isEmpty()){
            return TextInputValidationType.EmptyField
        }
        return TextInputValidationType.Valid
    }
}

class ValidateEmailInputUseCase @Inject constructor(){
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

class ValidatePasswordInputUseCase @Inject constructor() {
    operator fun invoke(input:String):PasswordInputValidationType{
        if(input.isEmpty()){
            return PasswordInputValidationType.EmptyField
        }
        return PasswordInputValidationType.Valid
    }
}

class ValidateConfirmPasswordInputUseCase @Inject constructor() {
    operator fun invoke(password:String,confirmPassword:String): PasswordConfirmInputValidationType {
        if(confirmPassword.isEmpty()){
            return PasswordConfirmInputValidationType.EmptyField
        }
        if(password != confirmPassword){
            return PasswordConfirmInputValidationType.WrongPassword
        }
        return PasswordConfirmInputValidationType.Valid
    }
}