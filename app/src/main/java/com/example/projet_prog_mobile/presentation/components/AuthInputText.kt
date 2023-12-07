package com.example.projet_prog_mobile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.projet_prog_mobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthInputText(
    modifier: Modifier,
    value: () -> String,
    placeholder:String,
    visualTransformation:VisualTransformation? = VisualTransformation.None,
    keyboardType: KeyboardType,
    onValueChanged:(String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = {},
    error:()->String?
) {
    Column( modifier=modifier) {
        OutlinedTextField(
            modifier=Modifier.fillMaxWidth(),
            value = value(),
            onValueChange = onValueChanged,
            visualTransformation = visualTransformation ?: VisualTransformation.None,
            singleLine = true,
            placeholder = { Text(text = placeholder,color = colorResource(id = R.color.dark_grey)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = colorResource(id = R.color.main_pink),
                focusedBorderColor = colorResource(id = R.color.main_pink),
                unfocusedBorderColor =  colorResource(id = R.color.dark_grey),
                textColor = colorResource(id = R.color.main_pink)
            ),
            trailingIcon=trailingIcon
        )
        if(error() != null) Text(text = error() ?: "", color= MaterialTheme.colors.error)
    }


}