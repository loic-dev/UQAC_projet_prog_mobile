package com.example.projet_prog_mobile.data.local.scan

import androidx.annotation.StringRes

data class ScanModel(
    val displayValue: String,
    @StringRes val scanFormatId: Int,
    val scanType: ScanType
)

enum class ScanType {
    Text
}
