package com.example.projet_prog_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.projet_prog_mobile.presentation.ui.theme.Projet_prog_mobileTheme
import com.example.projet_prog_mobile.util.Navigation
import com.example.projet_prog_mobile.util.rememberWindowInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}