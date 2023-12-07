package com.example.projet_prog_mobile.data.repository

import com.example.projet_prog_mobile.data.api.ApiException
import com.example.projet_prog_mobile.data.api.invoice.InvoiceRemoteDataSource
import com.example.projet_prog_mobile.data.api.invoice.ListInvoiceResponse
import com.example.projet_prog_mobile.domain.repository.InvoiceRepository

class InvoiceRepositoryImpl(
    private val invoiceRemoteDataSource: InvoiceRemoteDataSource
): InvoiceRepository {
    override suspend fun getInvoiceList(): ListInvoiceResponse {
        try {
            return invoiceRemoteDataSource.getInvoiceList()
        } catch (e: ApiException){
            throw e
        }

    }

}