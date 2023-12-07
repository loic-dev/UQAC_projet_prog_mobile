package com.example.projet_prog_mobile.data.api.invoice

data class ListInvoiceResponse(
    val items:List<InvoiceLink>
)

data class CreateInvoiceResponse(
    val name:String,
    val bucket:String
)
data class InvoiceLink(
    val name:String,
    val bucket:String
)