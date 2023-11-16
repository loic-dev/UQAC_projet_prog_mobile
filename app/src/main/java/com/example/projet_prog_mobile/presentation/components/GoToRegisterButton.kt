// Fichier GoToRegisterButton.kt

package com.example.projet_prog_mobile.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier



@Composable
fun GoToRegisterButton(
    modifier: Modifier = Modifier,
    text: String,

) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = {
        }
    ) {
        Text(text = text)
    }
}
