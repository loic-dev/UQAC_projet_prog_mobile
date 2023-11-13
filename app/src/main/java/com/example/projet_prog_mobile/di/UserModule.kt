package com.example.projet_prog_mobile.di

import com.example.projet_prog_mobile.data.api.user.UserApi
import com.example.projet_prog_mobile.data.api.user.UserRemoteDataSource
import com.example.projet_prog_mobile.data.local.AppDatabase
import com.example.projet_prog_mobile.data.local.user.UserDao
import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import com.example.projet_prog_mobile.data.repository.UserRepositoryImpl
import com.example.projet_prog_mobile.domain.repository.UserRepository
import com.example.projet_prog_mobile.domain.use_cases.ValidateLoginInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCase()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userLocalDataSource: UserLocalDataSource,
        userRemoteDataSource: UserRemoteDataSource
    ): UserRepository {
        return UserRepositoryImpl(userLocalDataSource, userRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(userApi: UserApi): UserRemoteDataSource {
        return UserRemoteDataSource(userApi)
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }


    @Provides
    @Singleton
    fun provideUserLocalDataSource(userDao: UserDao): UserLocalDataSource {
        return UserLocalDataSource(userDao)
    }



}

