package com.example.projet_prog_mobile.presentation.state

import androidx.camera.view.PreviewView
import com.example.projet_prog_mobile.data.local.product.Product
import com.example.projet_prog_mobile.data.local.scan.ScanModel

data class ScannerState(
    val previewView: PreviewView? = null,
    val scan: ScanModel? = null,
    val product:Product? = null,
    val error:String? = null,
    val loading: Boolean = false,
    val showBottomSheet: Boolean = false,
    val showCameraRequiredDialog: Boolean = false
)