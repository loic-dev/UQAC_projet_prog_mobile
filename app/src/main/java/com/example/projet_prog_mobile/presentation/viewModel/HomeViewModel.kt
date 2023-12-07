package com.example.projet_prog_mobile.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_prog_mobile.data.api.ApiException
import com.example.projet_prog_mobile.domain.repository.InvoiceRepository
import com.example.projet_prog_mobile.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val invoiceRepository: InvoiceRepository
): ViewModel() {
    var homeState by mutableStateOf(HomeState())
        private set

    fun getInvoices(){
        viewModelScope.launch {
            try {
                val invoices = invoiceRepository.getInvoiceList()
                homeState = homeState.copy(invoices = invoices.items)
            } catch (e: ApiException) {
                throw e
            } finally {
                homeState = homeState.copy(loadingInvoices = false)
            }
        }
    }
}