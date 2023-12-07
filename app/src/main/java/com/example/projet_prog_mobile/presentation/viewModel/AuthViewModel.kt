package com.example.projet_prog_mobile.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_prog_mobile.domain.repository.UserRepository
import com.example.projet_prog_mobile.presentation.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _authState = MutableLiveData(AuthState())
    val authState: LiveData<AuthState> get() = _authState

    fun checkAuth(){
        _authState.value = _authState.value?.copy(loading = true)
        viewModelScope.launch {
            try{
                val authResult = userRepository.authUser()
                _authState.value = _authState.value?.copy(auth = authResult)
            }catch(e: Exception){
                _authState.value = _authState.value?.copy(auth = false)
            }finally {
                _authState.value = _authState.value?.copy(loading = false)
            }
        }
    }

}