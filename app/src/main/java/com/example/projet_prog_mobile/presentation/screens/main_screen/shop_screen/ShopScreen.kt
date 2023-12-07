package com.example.projet_prog_mobile.presentation.screens.main_screen.shop_screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.components.ShopCardItem
import com.example.projet_prog_mobile.presentation.viewModel.ShopViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ShopScreen(modifier: Modifier,
               navController: NavHostController,
               shopViewModel: ShopViewModel = hiltViewModel()
) {

    val shopState by shopViewModel.shopState.collectAsState()
    var totalPrice by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(shopState.products) {
        totalPrice = shopViewModel.getTotalPrice()
    }

    LaunchedEffect(true) {
        shopViewModel.getProducts()
    }


    Column(modifier=modifier.padding(bottom=50.dp)) {
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
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Text("Scanning", color= colorResource(id = R.color.white))
        }

        LazyColumn(modifier=Modifier.weight(1f)) {
            val products = shopState.products
            if(products.isNotEmpty()){
                items(products) { product ->
                    val price = (product.price.toDouble()*product.quantity).toString()
                    val dismissState = rememberDismissState()
                    if (dismissState.isDismissed(DismissDirection.EndToStart)){
                        shopViewModel.onSwipeToDelete(product)
                    }
                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier,
                        dismissThresholds = { direction ->
                            FractionalThreshold(if (direction == DismissDirection.StartToEnd) 0.25f else 0.5f)
                        },
                        directions = setOf(
                            DismissDirection.EndToStart
                        ),
                        background = {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color = Color.Red)
                                    .padding(end = 16.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.White
                                )
                            }
                        },
                        dismissContent = {
                            ShopCardItem(
                                title = product.title,
                                price = price,
                                image = product.image,
                                quantity = product.quantity
                            )
                        }
                    )

                }
            } else {
                item {
                    Text(text = "No product", textAlign = TextAlign.Center)
                }

            }
        }
        Divider()
        ListItem(
            modifier = Modifier.height(50.dp),
            headlineText = { },
            supportingText = { },
            trailingContent = { Text(text = "${String.format("%.2f", totalPrice)} $", fontSize = 16.sp,
                fontWeight = FontWeight.Bold, color=colorResource(id = R.color.main_pink)) },
            leadingContent = {
                Text(text = "Total")
            },
            colors= ListItemDefaults.colors(
                containerColor = colorResource(id = R.color.white)
            )

        )
        if(shopState.products.isNotEmpty()){
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal=40.dp),
                colors= ButtonDefaults.buttonColors(
                    containerColor= colorResource(id = R.color.main_pink)
                ),
                onClick = { shopViewModel.onSaveInvoice() }) {
                if(shopState.loadingOnSaveInvoice){
                    Text(text = "Loading")
                } else {
                    Text(text = "Save invoice as file")
                }

            }
        }

    }
}