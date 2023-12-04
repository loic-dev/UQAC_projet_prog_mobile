package com.example.projet_prog_mobile.presentation.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.components.ScanSheet
import com.example.projet_prog_mobile.presentation.state.ScannerState


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScannerContainer(
    bottomSheetState: ModalBottomSheetState,
    uiState: ScannerState,
    notificationMessage:String?,
    clearNotification:()->Unit,
    addProductToShop: () -> Unit,
    onChangeQuantity:(Int) -> Unit,
    onChangePrice:(String) -> Unit,
    hideBottomSheet:()->Unit,
    backShoppingPage:()->Unit,
) {

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = 14.dp,
            topEnd = 14.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        scrimColor = Color.Transparent,
        sheetContent = {
            Surface(modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp)) {
                if (uiState.loading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            color = colorResource(id = R.color.main_pink),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                } else {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.white))) {
                        IconButton(
                            onClick = { hideBottomSheet() },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = colorResource(R.color.main_pink)
                            )
                        }
                        uiState.product?.let {
                            ScanSheet(
                                product = it,
                                modifier = Modifier,
                                onChangeQuantity = onChangeQuantity,
                                onChangePrice = onChangePrice,
                                hideBottomSheet = { hideBottomSheet() },
                                addProductToShop = { addProductToShop() }
                            )
                        } ?: Text(
                            text = uiState.error.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }


        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            if (uiState.previewView != null) {
                if (notificationMessage != null) {
                    AlertDialog(
                        onDismissRequest = {
                            clearNotification()
                        },
                        title = {
                            Text(text = notificationMessage)
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                clearNotification()
                            }) {
                                Text("OK")
                            }
                        }
                    )
                }
                AndroidView(
                    factory = { uiState.previewView },
                    modifier = Modifier.fillMaxSize()
                )

            }

            if(!bottomSheetState.isVisible){
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(colorResource(id = R.color.main_pink), shape = CircleShape)
                    )
                    IconButton(
                        onClick = { backShoppingPage() },
                        modifier = Modifier
                            .size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = colorResource(R.color.white)
                        )
                    }
                }
            }


        }
    }
}