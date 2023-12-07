package com.example.projet_prog_mobile.presentation.screens.main_screen.home_screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.viewModel.HomeViewModel


@Composable
fun HomeScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()) {
    val localUriHandler = LocalUriHandler.current

    LaunchedEffect(true) {
        homeViewModel.getInvoices()
    }


    Column(modifier=modifier.padding(bottom=50.dp)) {
        Text(text = "HOME",
            style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.light_black)
        ))
        Text(modifier=Modifier.padding(top=10.dp),
            text = "Your invoices :",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.light_black)
            ))
        if(!homeViewModel.homeState.loadingInvoices){
            LazyColumn(modifier=Modifier.weight(1f)) {
                items(homeViewModel.homeState.invoices){
                    val fullName = it.name
                    val parts = fullName.split("/")
                    val name = if (parts.size > 1) parts[1] else fullName
                    val folder = if (parts.size > 1) parts[0] else fullName
                    Card(modifier= Modifier
                        .padding(vertical = 10.dp)
                        .clickable {
                            localUriHandler.openUri("https://firebasestorage.googleapis.com/v0/b/${it.bucket}/o/${folder}%2F${name}?alt=media")
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize().padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = name,
                                fontSize=16.sp,
                                fontWeight=FontWeight.Bold,
                                color= colorResource(id = R.color.main_pink),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}