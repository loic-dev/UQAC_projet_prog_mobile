package com.example.projet_prog_mobile.domain.repository
import com.example.projet_prog_mobile.data.api.invoice.ListInvoiceResponse

interface InvoiceRepository {
    suspend fun getInvoiceList(): ListInvoiceResponse
}