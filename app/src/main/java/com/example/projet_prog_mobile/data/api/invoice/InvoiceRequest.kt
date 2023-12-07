package com.example.projet_prog_mobile.data.api.invoice

import com.example.projet_prog_mobile.data.local.product.Product

data class InvoiceRequest(
    val invoice:List<Product>
)