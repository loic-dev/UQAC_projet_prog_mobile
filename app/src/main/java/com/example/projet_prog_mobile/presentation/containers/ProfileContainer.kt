package com.example.projet_prog_mobile.presentation.containers


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.components.ProfileField

@Composable
fun ProfileContainer(
    firstname: String,
    lastname: String,
    email: String,
    modifier: Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier= Modifier.padding(bottom = 5.dp, top = 20.dp).fillMaxWidth(),
            fontSize = 16.sp,
            textAlign=TextAlign.Start,
            text = stringResource(R.string.profile_firstname)
        )
        ProfileField(
            modifier = modifier,
            value = firstname
        )
        Text(
            modifier= Modifier.padding(bottom = 5.dp,top = 20.dp).fillMaxWidth(),
            fontSize = 16.sp,
            text = stringResource(R.string.profile_lastname)
        )
        ProfileField(
            modifier = modifier,
            value = lastname
        )
        Text(
            modifier= Modifier.padding(bottom = 5.dp,top = 20.dp).fillMaxWidth(),
            fontSize = 16.sp,
            text = stringResource(R.string.profile_email)
        )
        ProfileField(
            modifier = modifier,
            value = email
        )
    }
    }


