package com.example.projet_prog_mobile.data.local.scan


import com.google.mlkit.vision.barcode.common.Barcode
import com.example.projet_prog_mobile.R

fun Barcode.toScan(): ScanModel {
    return ScanModel(
        displayValue = displayValue ?: "",
        scanFormatId = when(format) {
            Barcode.FORMAT_UPC_A -> R.string.scan_format_upc_a
            Barcode.FORMAT_UPC_E -> R.string.scan_format_upc_e
            else -> R.string.scan_format_unknown
        },
        scanType = ScanType.Text
    )
}