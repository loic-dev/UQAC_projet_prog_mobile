package com.example.projet_prog_mobile.data.api.invoice
import com.example.projet_prog_mobile.data.api.handleApiCall
import com.example.projet_prog_mobile.data.local.product.Product
import javax.inject.Inject

class InvoiceRemoteDataSource @Inject constructor(
    private val invoiceAPI: InvoiceAPI
) {
    suspend fun getInvoiceList(): ListInvoiceResponse {
        return handleApiCall { invoiceAPI.getInvoicesList() }
    }

    suspend fun createInvoice(invoice: List<Product>): CreateInvoiceResponse {
        return handleApiCall { invoiceAPI.createInvoice(InvoiceRequest(invoice)) }
    }
}