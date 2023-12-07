package com.example.projet_prog_mobile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_prog_mobile.data.local.product.Product
import com.example.projet_prog_mobile.domain.repository.ShopRepository
import com.example.projet_prog_mobile.presentation.state.ShopState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val shopRepository: ShopRepository
): ViewModel() {

    private val _shopState = MutableStateFlow(ShopState())
    val shopState = _shopState.asStateFlow()


    fun getProducts(){
        viewModelScope.launch {
            val shop = shopRepository.getListProduct()
            _shopState.update { it.copy(products = shop) }
        }
    }

    fun onSwipeToDelete(product: Product){
        viewModelScope.launch {
            shopRepository.deleteProduct(product)
            getProducts()
        }
    }

    private fun clearShop(){
        viewModelScope.launch {
            shopRepository.clearShop()
            getProducts()
        }
    }

    fun onSaveInvoice(){
        viewModelScope.launch {
            _shopState.update { it.copy(loadingOnSaveInvoice = true) }
            shopRepository.createInvoice()
            clearShop()
            _shopState.update { it.copy(loadingOnSaveInvoice = false) }
        }
    }



    fun getTotalPrice(): Double {
        var globalPrice = 0.00
        _shopState.value.products.forEach { product ->
            val price = product.price.toDouble() * product.quantity
            globalPrice += price
        }
        return globalPrice
    }

}