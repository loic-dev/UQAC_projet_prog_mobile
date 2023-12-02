package com.example.projet_prog_mobile.presentation.viewModel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.data.api.ApiException
import com.example.projet_prog_mobile.data.local.product.Product
import com.example.projet_prog_mobile.domain.repository.ScanRepository
import com.example.projet_prog_mobile.presentation.state.ScannerState
import com.example.projet_prog_mobile.util.ShopWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val scanRepository: ScanRepository,
    @ApplicationContext private val application: Application
): ViewModel() {

    private val vmState = MutableStateFlow(ScannerState())
    private val _notificationMessage = MutableLiveData<String?>()
    val notificationMessage: LiveData<String?> = _notificationMessage


    val scannerState = vmState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmState.value
    )

    fun clearNotification(){
        _notificationMessage.value = null
    }

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
                    val averagePrice = (result.items[0].lowest_recorded_price+result.items[0].lowest_recorded_price)/2
                    val createProduct = Product(
                        uid= UUID.randomUUID().toString(),
                        title = result.items[0].title,
                        quantity = 0,
                        price = averagePrice.toDouble()
                    )
                    vmState.update { it.copy( loading = false, product = createProduct ) }
                }

            } catch (e: ApiException) {
                vmState.update { it.copy( loading = false, showBottomSheet = false, error = e.message) }
            }
        }
    }

    fun changeProductQuantity(quantity:Int){
        viewModelScope.launch {
            vmState.update { currentState ->
                currentState.copy(product = currentState.product?.copy(quantity = quantity))
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

     fun addProductToWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workerRequest = OneTimeWorkRequestBuilder<ShopWorker>()
            .setInputData(
                workDataOf(
                    ShopWorker.KEY_TITLE to vmState.value.product!!.title,
                    ShopWorker.KEY_QUANTITY to vmState.value.product!!.quantity,
                    ShopWorker.KEY_PRICE to vmState.value.product!!.price
                )
            )
            .setConstraints(constraints)
            .build()
         val workManager = WorkManager.getInstance(application)
         workManager.enqueue(workerRequest)
         val workInfoLiveData = workManager.getWorkInfoByIdLiveData(workerRequest.id)
         workInfoLiveData.observeForever { workInfo ->
             if (workInfo != null && workInfo.state.isFinished) {
                     _notificationMessage.value =
                         application.getString(R.string.scan_screen_product_added_to_shop_success_notif)
                 } else {
                    _notificationMessage.value = application.getString(R.string.scan_screen_product_added_to_shop_error_notif)
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

