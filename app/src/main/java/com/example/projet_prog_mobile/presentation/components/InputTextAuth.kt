package com.example.projet_prog_mobile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextEntryModule(
    modifier: Modifier = Modifier,
    description:String,
    hint:String,
    textValue:String,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    onValueChanged:(String) -> Unit,
    isSelected: Boolean = false,
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
            colors = TextFieldDefaults.textFieldColors(
                textColor = if (isSelected) Color(0xFFDE3163) else Color.Black,
                cursorColor = if (isSelected) Color(0xFFDE3163) else Color.Black,
                focusedIndicatorColor = if (isSelected) Color(0xFFDE3163) else Color.Black,
                unfocusedIndicatorColor = if (isSelected) Color(0xFFDE3163) else Color.Black
            )
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