package com.example.projet_prog_mobile.data.local.scan

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScanLocalDataSource @Inject constructor(
    private val scanLDS: ScanLDS
) {

    fun getLatestScan(): Flow<ScanModel> {
        return scanLDS.getLatestScan()
    }

    suspend fun getCameraPreview(lifecycleOwner: LifecycleOwner): PreviewView{
        return scanLDS.getCameraPreview(lifecycleOwner)
    }

     fun pauseScan(){
        return scanLDS.pauseScan()
    }

     fun resumeScan(){
        return scanLDS.resumeScan()
    }

}