package com.example.projet_prog_mobile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.projet_prog_mobile.R


@Composable
fun ShopCardItem(
    title:String,
    quantity:Int,
    price:String,
    image:String?
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.white))
                .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
        ) {
            Box(
                Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if(image !== null){
                    AsyncImage(
                        model = image,
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

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .padding(start=20.dp, end=10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,

                    )
                Text(
                    text = "Quantity: $quantity",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,

                    )
            }
            Text(text="$price $",fontSize = 16.sp, fontWeight = FontWeight.Bold, color= colorResource(id = R.color.main_pink))

        }
        Divider()
    }

}
