package com.example.dictionmaster.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.dictionmaster.R
import com.example.dictionmaster.core.ui.theme.DarkBlue
import com.example.dictionmaster.core.ui.theme.Grey
import com.example.dictionmaster.core.ui.theme.LocalDimensions
import com.example.dictionmaster.core.ui.theme.Typography

@Composable
fun SearchTextField(
    word: String,
    onWordChange: (String) -> Unit,
    scope: ColumnScope,
) = scope.run {
    val dimensions = LocalDimensions.current

    TextField(
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .fillMaxWidth()
            .padding(horizontal = dimensions.huge)
            .defaultMinSize(minHeight = dimensions.big),
        value = word,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
        ),
        onValueChange = {
            onWordChange(it)
        },
        textStyle = Typography.titleLarge.copy(
            color = DarkBlue,
            textAlign = TextAlign.Center,
        ),
        placeholder = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = stringResource(R.string.search_hint),
                    style = Typography.titleSmall,
                    textAlign = TextAlign.Center,
                    color = Grey,
                )
            }
        },
        singleLine = true,
    )
}