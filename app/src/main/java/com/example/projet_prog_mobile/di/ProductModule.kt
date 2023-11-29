package com.example.projet_prog_mobile.di

import com.example.projet_prog_mobile.data.api.product.ProductApi
import com.example.projet_prog_mobile.data.api.product.ProductRemoteDataSource
import com.example.projet_prog_mobile.data.local.product.ProductDao
import com.example.projet_prog_mobile.data.local.product.ProductLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Provides
    @Singleton
    fun provideProductRemoteDataSource(productApi: ProductApi): ProductRemoteDataSource {
        return ProductRemoteDataSource(productApi)
    }

    @Provides
    @Singleton
    fun provideProductLocalDataSource(productDao: ProductDao): ProductLocalDataSource {
        return ProductLocalDataSource(productDao)
    }



}