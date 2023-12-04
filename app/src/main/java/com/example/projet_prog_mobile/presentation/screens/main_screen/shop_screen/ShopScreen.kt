package com.example.projet_prog_mobile.presentation.screens.main_screen.shop_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.viewModel.ShopViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(modifier: Modifier,
               navController: NavHostController,
               shopViewModel: ShopViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        shopViewModel.onLoad()
    }

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
        Column {
            val products = shopViewModel.shopState.products
            var globalPrice = 0.00
            if(products.isNotEmpty()){
                repeat(products.size) { index ->
                    val product = products[index]
                    val price = (product.price.toDouble()*product.quantity).toString()
                    globalPrice += price.toDouble()
                    ListItem(
                        headlineText = { Text(
                            text = product.title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        ) },
                        supportingText = { Text("Quantity: ${product.quantity}") },
                        trailingContent = { Text(text="$price $",fontWeight = FontWeight.Bold, color=colorResource(id = R.color.main_pink)) },
                        leadingContent = {
                            if(product.image !== null){
                                AsyncImage(
                                    model = product.image,
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            } else {
                                Icon(
                                    Icons.Filled.QuestionMark,
                                    contentDescription = "Image not available",
                                )
                            }

                        }
                    )
                    Divider()
                }
                ListItem(
                    headlineText = { },
                    supportingText = { },
                    trailingContent = { Text(text = "${String.format("%.2f", globalPrice)} $", fontSize = 16.sp,
                        fontWeight = FontWeight.Bold, color=colorResource(id = R.color.main_pink)) },
                    leadingContent = {
                        Text(text = "Total")
                    }
                )
            }


        }


    }
}