package com.example.projet_prog_mobile.di

import com.example.projet_prog_mobile.data.api.product.ProductApi
import com.example.projet_prog_mobile.data.api.product.ProductRemoteDataSource
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

}