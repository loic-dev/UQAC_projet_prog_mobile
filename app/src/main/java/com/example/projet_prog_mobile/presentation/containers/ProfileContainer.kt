package com.example.projet_prog_mobile.presentation.containers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.presentation.components.AuthInputButton
import com.example.projet_prog_mobile.presentation.components.ProfileField

@Composable
fun ProfileContainer(
    isLoading: Boolean,
    firstname: String,
    lastname: String,
    email: String,
    modifier: Modifier,
    onLogoutButtonClick: () -> Unit,
) {
    Column(modifier=modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.profile_title)
                )
                Text(
                    modifier = Modifier.padding(top = 80.dp),
                    fontSize = 20.sp,
                    text = stringResource(R.string.profile_firstname)
                )
                ProfileField(
                    modifier = modifier,
                    value = firstname
                )
                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    fontSize = 20.sp,
                    text = stringResource(R.string.profile_lastname)
                )
                ProfileField(
                    modifier = modifier,
                    value = lastname
                )
                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    fontSize = 20.sp,
                    text = stringResource(R.string.profile_email)
                )
                ProfileField(
                    modifier = modifier,
                    value = email
                )
                AuthInputButton(
                    modifier=Modifier.padding(top = 60.dp),
                    buttonEnabled= { true },
                    isLoading= { isLoading },
                    onButtonClick= onLogoutButtonClick,
                    textButton=stringResource(R.string.profile_logout)
                )
            }
        }
    }
}
