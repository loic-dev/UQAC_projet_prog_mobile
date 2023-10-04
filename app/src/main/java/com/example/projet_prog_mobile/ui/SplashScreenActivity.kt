package com.example.projet_prog_mobile.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.ui.theme.Projet_prog_mobileTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit){
                delay(1.seconds)
                Intent(applicationContext, MainActivity::class.java).also {
                    startActivity(it)
                }
            }

            Projet_prog_mobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen("Android", modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun SplashScreen(name: String, modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Logo")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    Projet_prog_mobileTheme {
        SplashScreen("Android", modifier = Modifier)
    }
}