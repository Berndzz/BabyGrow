package com.hardus.babygrow.util.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonComponent(
    value: String, onSave: () -> Unit, isEnabled: Boolean = false
) {
    Button(
        onClick = {
            onSave.invoke()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(6.dp),
        enabled = isEnabled
    ) {
        Text(text = value, fontSize = 18.sp)
    }
}