package com.example.projet_prog_mobile.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_prog_mobile.domain.repository.UserRepository
import com.example.projet_prog_mobile.presentation.state.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    var profileState by mutableStateOf(ProfileState())
        private set

    fun onLoad() {
        profileState = profileState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val user = userRepository.getUser()
                if(user !== null){
                    Log.d("User : ", user.toString())
                    profileState = profileState.copy(
                        firstname = user.firstName.toString(),
                        lastname = user.lastName.toString(),
                        email = user.email.toString(),
                    )
                }
            } catch (e: Exception) {
                profileState = profileState.copy(errorMessageApi = e.message)
            } finally {
                profileState = profileState.copy(isLoading = false)
            }
        }
    }

    fun onDisconnect() {
        profileState = profileState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                userRepository.logout()
                profileState = profileState.copy(logoutSuccess = true)
            } catch (e: Exception) {
                profileState = profileState.copy(errorMessageApi = e.message)
            } finally {
                profileState = profileState.copy(isLoading = false)
            }
        }
    }
}