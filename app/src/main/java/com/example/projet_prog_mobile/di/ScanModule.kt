package com.example.projet_prog_mobile.di

import com.example.projet_prog_mobile.data.api.product.ProductRemoteDataSource
import com.example.projet_prog_mobile.data.local.scan.ScanLDS
import com.example.projet_prog_mobile.data.local.scan.ScanLocalDataSource
import com.example.projet_prog_mobile.data.repository.ScanRepositoryImpl
import com.example.projet_prog_mobile.domain.repository.ScanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScanModule {

    @Provides
    @Singleton
    fun provideScanRepository(
        scanLocalDataSource: ScanLocalDataSource,
        productRemoteDataSource: ProductRemoteDataSource,
    ): ScanRepository {
        return ScanRepositoryImpl(scanLocalDataSource,productRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideScanLocalDataSource(scanLDS: ScanLDS): ScanLocalDataSource {
        return ScanLocalDataSource(scanLDS)
    }
}