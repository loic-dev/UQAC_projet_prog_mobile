package com.example.projet_prog_mobile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextEntryModule(
    modifier: Modifier = Modifier,
    description:String,
    hint:String,
    textValue:String,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    onValueChanged:(String) -> Unit
) {

    Column(modifier = modifier){
        Text(text = description)
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textValue,
            onValueChange = onValueChanged,
            singleLine = true,
            placeholder = { Text(hint) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextEntryModulePreview(){
    TextEntryModule(
        description = "Email address",
        modifier = Modifier.fillMaxWidth(),
        hint = "test@gmail.com",
        textValue = "TextInput",
        onValueChanged = {},
    )
}