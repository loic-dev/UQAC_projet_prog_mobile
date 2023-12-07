package com.example.projet_prog_mobile.di

import com.example.projet_prog_mobile.data.api.user.UserApi
import com.example.projet_prog_mobile.data.api.user.UserRemoteDataSource
import com.example.projet_prog_mobile.data.local.user.UserDao
import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import com.example.projet_prog_mobile.data.repository.UserRepositoryImpl
import com.example.projet_prog_mobile.domain.repository.UserRepository
import com.example.projet_prog_mobile.domain.use_cases.ValidateAuthInputTextUseCase
import com.example.projet_prog_mobile.domain.use_cases.ValidateConfirmPasswordInputUseCase
import com.example.projet_prog_mobile.domain.use_cases.ValidateEmailInputUseCase
import com.example.projet_prog_mobile.domain.use_cases.ValidatePasswordInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideValidatePasswordInputUseCase(): ValidatePasswordInputUseCase {
        return ValidatePasswordInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateEmailInputUseCase(): ValidateEmailInputUseCase {
        return ValidateEmailInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateConfirmInputUseCase(): ValidateConfirmPasswordInputUseCase {
        return ValidateConfirmPasswordInputUseCase()
    }
    @Provides
    @Singleton
    fun provideValidateAuthInputTextUseCase(): ValidateAuthInputTextUseCase {
        return ValidateAuthInputTextUseCase()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userLocalDataSource: UserLocalDataSource,
        userRemoteDataSource: UserRemoteDataSource,
        ioDispatcher: CoroutineDispatcher

    ): UserRepository {
        return UserRepositoryImpl(userLocalDataSource, userRemoteDataSource,ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(userApi: UserApi): UserRemoteDataSource {
        return UserRemoteDataSource(userApi)
    }




    @Provides
    @Singleton
    fun provideUserLocalDataSource(userDao: UserDao): UserLocalDataSource {
        return UserLocalDataSource(userDao)
    }



}

