package com.example.projet_prog_mobile.presentation.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.data.local.product.Product
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanSheet(
    product: Product,
    onChangeQuantity:(Int)->Unit,
    onChangePrice:(String) -> Unit,
    hideBottomSheet:()->Unit,
    addProductToShop:()->Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Row{
            if(product.image !== null){
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                )
            }
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Divider()
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = product.price,
            label = { Text(text="Price",color= colorResource(id = R.color.main_pink))},
            onValueChange = {onChangePrice(it) },
            leadingIcon = {
                Text(text = "$")
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Decimal,
            ),
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = colorResource(id = R.color.main_pink),
                focusedBorderColor = colorResource(id = R.color.main_pink),
                unfocusedBorderColor =  colorResource(id = R.color.dark_grey),
                textColor = colorResource(id = R.color.main_pink)),
        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Quantity:")
            Slider(
                value = product.quantity.toFloat(),
                onValueChange = { onChangeQuantity(it.toInt()) },
                valueRange = 1f..10f,
                steps = 10,
                modifier = Modifier.weight(1f),
                colors = SliderDefaults.colors(
                    thumbColor = colorResource(id = R.color.main_pink),
                    activeTrackColor = colorResource(id = R.color.main_pink)
                )
            )
            Text(product.quantity.toString())
        }
        Button(
            onClick = {
                coroutineScope.launch {
                    addProductToShop()
                    hideBottomSheet()
                }
            },
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