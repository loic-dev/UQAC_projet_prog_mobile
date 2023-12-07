package com.example.projet_prog_mobile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.projet_prog_mobile.R

@Composable
fun ProfileField(
    modifier: Modifier,
    value: String,
) {

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    colorResource(id = R.color.dark_grey),
                    shape = MaterialTheme.shapes.small
                )
                .background(color = colorResource(id = R.color.light_grey),
                    shape = MaterialTheme.shapes.small)
        ) {
            Text(
                text = value,
                color = colorResource(id = R.color.main_pink),
                modifier = Modifier.padding(all = 7.dp)
            )
        }
    }
}