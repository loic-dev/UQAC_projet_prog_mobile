package com.example.projet_prog_mobile.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.projet_prog_mobile.R

@Composable
fun AuthInputButton (
    modifier: Modifier,
    buttonEnabled:() -> Boolean,
    isLoading:() -> Boolean,
    onButtonClick:() ->Unit,
    textButton:String
){
    Button(
        modifier= modifier.fillMaxWidth()
        .height(50.dp),
        onClick = { onButtonClick() },
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.main_pink),
            contentColor = colorResource(id = R.color.white),
            disabledContainerColor = colorResource(id = R.color.light_grey)),
        enabled = buttonEnabled()
    ){
        if(isLoading()){
            CircularProgressIndicator(
                color = colorResource(id = R.color.white),
                modifier = Modifier.size(20.dp)
            )
        }else{
            Text(text = textButton, color= colorResource(id = R.color.white))
        }
    }

}