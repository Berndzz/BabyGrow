package com.hardus.babygrow.util.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hardus.babygrow.R

@Composable
fun MyTextField(
    text: String,
    labelValue: String,
    imageVector: ImageVector,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false,
    focusRequester: FocusRequester,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
    placeholder: String? = null
) {
    OutlinedTextField(modifier = modifier.focusRequester(focusRequester),
        label = { Text(text = labelValue) },
        value = text,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = colorResource(id = R.color.black),
            focusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
        ),

        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onValueChange = {
            onTextSelected(it)
        },
        placeholder = {
            if (placeholder != null) {
                Text(text = placeholder)
            }
        },
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = "")
        },
        isError = !errorStatus,
        supportingText = {
            if (!errorStatus) {
                Text("Required")
            }
        }
    )
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun OptionalTextArea(
    optionalText: String,
    onOptionalText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = optionalText,
        onValueChange = onOptionalText,
        label = { Text("Keterangan Tambahan") },
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp), // Atur tinggi sesuai kebutuhan
        textStyle = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Start),
        maxLines = 10, // Atur jumlah maksimal baris atau biarkan tidak terbatas
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = colorResource(id = R.color.black),
            focusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
        ),
    )
}