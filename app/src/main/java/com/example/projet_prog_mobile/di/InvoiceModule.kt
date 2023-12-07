package com.example.projet_prog_mobile.di

import com.example.projet_prog_mobile.data.api.invoice.InvoiceAPI
import com.example.projet_prog_mobile.data.api.invoice.InvoiceRemoteDataSource
import com.example.projet_prog_mobile.data.repository.InvoiceRepositoryImpl
import com.example.projet_prog_mobile.domain.repository.InvoiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InvoiceModule {
    @Provides
    @Singleton
    fun provideInvoiceRemoteDataSource(invoiceAPI: InvoiceAPI): InvoiceRemoteDataSource {
        return InvoiceRemoteDataSource(invoiceAPI)
    }

    @Provides
    @Singleton
    fun provideInvoiceRepository(
        invoiceRemoteDataSource: InvoiceRemoteDataSource,
    ): InvoiceRepository {
        return InvoiceRepositoryImpl(invoiceRemoteDataSource)
    }
}