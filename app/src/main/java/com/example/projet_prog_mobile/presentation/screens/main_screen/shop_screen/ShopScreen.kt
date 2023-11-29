package com.example.projet_prog_mobile.presentation.screens.main_screen.shop_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projet_prog_mobile.R

@Composable
fun ShopScreen(modifier: Modifier,
               navController: NavHostController) {
    Column(modifier=modifier) {
        Text(text = "SHOPPING",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.light_black)
            ))

        Button(
            onClick = {
                navController.navigate("scan_screen")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor= colorResource(id = R.color.main_pink)
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Scanning", color= colorResource(id = R.color.white))
        }
    }
}