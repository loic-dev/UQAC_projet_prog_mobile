package com.example.projet_prog_mobile.domain.repository

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.projet_prog_mobile.data.api.product.ProductResponse
import com.example.projet_prog_mobile.data.local.scan.ScanModel
import kotlinx.coroutines.flow.Flow

interface ScanRepository {
    fun getLatestScan(): Flow<ScanModel>

    suspend fun getCameraPreview(lifecycleOwner: LifecycleOwner): PreviewView

    suspend fun pauseScan()

    suspend fun resumeScan()

    suspend fun getProductByUPC(upc:String): ProductResponse
}