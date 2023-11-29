package com.example.projet_prog_mobile.presentation.viewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_prog_mobile.data.api.ApiException
import com.example.projet_prog_mobile.domain.repository.ScanRepository
import com.example.projet_prog_mobile.presentation.state.ScannerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val scanRepository: ScanRepository
): ViewModel() {

    private val vmState = MutableStateFlow(ScannerState())

    val scannerState = vmState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmState.value
    )

    init {
        viewModelScope.launch {
            try {
                scanRepository.getLatestScan().collectLatest { scan ->
                    if (scan.displayValue.isNotBlank()) {
                        scanRepository.pauseScan()
                        vmState.update { it.copy( scan = scan,loading = true, showBottomSheet = true ) }
                    }
                }
            } catch (e: Exception) {
                vmState.update { it.copy( error = e.message.toString(), loading = false, showBottomSheet = true ) }
            }
        }

    }

    suspend fun getUpcProduct(){
        viewModelScope.launch {
            try {
                val result = scanRepository.getProductByUPC(vmState.value.scan!!.displayValue)
                if(result.items.isEmpty()){
                    vmState.update { it.copy( loading = false, error = "Product not found" ) }
                } else {
                    vmState.update { it.copy( loading = false, product = result.items[0] ) }
                }

            } catch (e: ApiException) {
                vmState.update { it.copy( loading = false, showBottomSheet = false, error = e.message) }
            }
        }
    }


    fun onEvent(event: ScannerEvent) {
        when(event) {
            is ScannerEvent.CreatePreviewView -> {
                viewModelScope.launch {
                    val previewView = scanRepository.getCameraPreview(event.lifecycleOwner)
                    vmState.update { it.copy( previewView = previewView ) }
                }
            }
            ScannerEvent.BottomSheetShown -> {
                viewModelScope.launch { scanRepository.pauseScan() }
                vmState.update { it.copy( showBottomSheet = false ) }
            }
            ScannerEvent.BottomSheetHidden -> {
                viewModelScope.launch { scanRepository.resumeScan() }
            }
            is ScannerEvent.CameraRequiredDialogVisibility -> {
                vmState.update { it.copy( showCameraRequiredDialog = event.show) }
            }
        }
    }
}
sealed class ScannerEvent {
    data class CreatePreviewView(val lifecycleOwner: LifecycleOwner): ScannerEvent()
    data object BottomSheetShown: ScannerEvent()
    data object BottomSheetHidden: ScannerEvent()
    data class CameraRequiredDialogVisibility(val show: Boolean): ScannerEvent()
}

