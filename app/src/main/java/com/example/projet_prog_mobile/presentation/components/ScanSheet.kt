package com.example.projet_prog_mobile.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.data.api.product.ProductItem

@Composable
fun ScanSheet(
    product: ProductItem,
    modifier: Modifier = Modifier,
) {
    val averagePrice = (product.lowest_recorded_price+product.highest_recorded_price)/2
    var selectedQuantity by remember { mutableIntStateOf(1) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Text(
            text = product.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Price : $averagePrice $",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
        Divider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Quantity:")
            Slider(
                value = selectedQuantity.toFloat(),
                onValueChange = {
                    selectedQuantity = it.toInt()
                },
                valueRange = 1f..10f,
                steps = 10,
                modifier = Modifier.weight(1f),
                colors = SliderDefaults.colors(
                    thumbColor = colorResource(id = R.color.main_pink),
                    activeTrackColor = colorResource(id = R.color.main_pink)
                )
            )
            Text(selectedQuantity.toString())
        }
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor= colorResource(id = R.color.main_pink)
            )
        ) {
            Text("Add product")
        }
    }
}