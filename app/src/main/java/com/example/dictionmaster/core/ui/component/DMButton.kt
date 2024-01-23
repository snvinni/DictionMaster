package com.example.dictionmaster.core.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionmaster.core.ui.theme.Blue
import com.example.dictionmaster.core.ui.theme.Typography

@Composable
fun DMButton(
    text: String,
    paddingValues: PaddingValues,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    Button(
        enabled = isEnabled,
        onClick = { onClick() },
        modifier = modifier
            .padding(paddingValues)
            .height(64.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        content = {
            Text(
                text = text,
                style = Typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.8.sp,
                ),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue,
            disabledContainerColor = Color.Gray,
        ),
    )
}