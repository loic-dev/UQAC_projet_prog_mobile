package com.example.projet_prog_mobile.data.repository

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.projet_prog_mobile.data.api.product.ProductRemoteDataSource
import com.example.projet_prog_mobile.data.api.product.ProductResponse
import com.example.projet_prog_mobile.data.local.scan.ScanLocalDataSource
import com.example.projet_prog_mobile.data.local.scan.ScanModel
import com.example.projet_prog_mobile.domain.repository.ScanRepository
import kotlinx.coroutines.flow.Flow

class ScanRepositoryImpl(
    private val scanLocalDataSource: ScanLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource,
): ScanRepository {

        override fun getLatestScan(): Flow<ScanModel> = scanLocalDataSource.getLatestScan()

        override suspend fun getCameraPreview(lifecycleOwner: LifecycleOwner): PreviewView =
            scanLocalDataSource.getCameraPreview(lifecycleOwner)

        override suspend fun pauseScan() = scanLocalDataSource.pauseScan()

        override suspend fun resumeScan() = scanLocalDataSource.resumeScan()
        override suspend fun getProductByUPC(upc:String): ProductResponse {
            return productRemoteDataSource.getProductByUpc(upc)
        }

}
