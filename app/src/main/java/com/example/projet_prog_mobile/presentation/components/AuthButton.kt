package com.example.projet_prog_mobile.presentation.components
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled:Boolean = true,
    onButtonClick: () -> Unit,
    isLoading: Boolean,

) {
    Button(
        modifier = modifier,
        onClick = { onButtonClick() },
        enabled = enabled
    ){
        if(isLoading){
            Text(text = "loading...")
        }else{
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthButtonPreview() {
    AuthButton(
        text = "Login",
        onButtonClick = { },
        isLoading = true,
        modifier = Modifier.fillMaxWidth()
    )
}