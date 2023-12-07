package com.example.projet_prog_mobile.presentation.state
import com.example.projet_prog_mobile.data.api.invoice.InvoiceLink

data class HomeState (
    val invoices:List<InvoiceLink> = listOf(),
    val loadingInvoices:Boolean = true
)