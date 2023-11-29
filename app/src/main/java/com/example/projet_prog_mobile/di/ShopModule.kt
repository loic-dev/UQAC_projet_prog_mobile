package com.example.projet_prog_mobile.di

import com.example.projet_prog_mobile.data.local.product.ProductLocalDataSource
import com.example.projet_prog_mobile.data.repository.ShopRepositoryImpl
import com.example.projet_prog_mobile.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShopModule {

    @Provides
    @Singleton
    fun provideShopRepository(
        productLocalDataSource: ProductLocalDataSource,
        ioDispatcher: CoroutineDispatcher
    ): ShopRepository {
        return ShopRepositoryImpl(productLocalDataSource, ioDispatcher)
    }




}