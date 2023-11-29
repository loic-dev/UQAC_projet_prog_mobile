package com.example.projet_prog_mobile.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_prog_mobile.domain.repository.ShopRepository
import com.example.projet_prog_mobile.presentation.state.ShopState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val shopRepository: ShopRepository
): ViewModel() {

    var shopState by mutableStateOf(ShopState())
        private set


    fun onLoad(){
        viewModelScope.launch {
            val shop = shopRepository.getListProduct()
            shopState = shopState.copy(products = shop)
        }
    }

}