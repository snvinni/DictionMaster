package com.example.dictionmaster.feature.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionmaster.R
import com.example.dictionmaster.core.ui.theme.Blue
import com.example.dictionmaster.core.ui.theme.DarkBlue
import com.example.dictionmaster.core.ui.theme.DictionMasterTheme
import com.example.dictionmaster.core.ui.theme.LightGrey
import com.example.dictionmaster.core.ui.theme.Typography

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel()
) {
//    SearchScreen(
//        onClick = {
//            viewModel.onSearchClick()
//        }
//    )
}

@Composable
fun SearchScreen(
    onClick: () -> Unit
) {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Spacer(modifier = Modifier.height(44.dp))

                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_english),
                        contentDescription = "ENGLISH",
                        modifier = Modifier.padding(
                            8.dp
                        )
                    )

                    Text(
                        text = "ENGLISH",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                        style = Typography.bodyMedium.copy(
                            letterSpacing = 1.8.sp,
                        ),
                        color = DarkBlue,
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(modifier = Modifier.height(124.dp))

                SearchTextField(
                    scope = this@Column,
                )
            }

            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(28.dp)
                    .height(64.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                content = {
                    Text(
                        text = "SEARCH",
                        style = Typography.bodyMedium.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.8.sp,
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                ),
            )
        }
    }
}

@Composable
fun SearchTextField(
    scope: ColumnScope,
) = scope.run {
    TextField(
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .fillMaxWidth()
            .padding(horizontal = 64.dp)
            .defaultMinSize(minHeight = 38.dp),
        value = "Education",
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
        ),
        onValueChange = {},
        textStyle = Typography.bodyLarge.copy(
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = DarkBlue,
        ),
        placeholder = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Type a word...",
                    style = Typography.bodyLarge.copy(
                        fontSize = 32.sp
                    ),
                    textAlign = TextAlign.Center,
                    color = LightGrey,
                )
            }
        },
        singleLine = true,
    )
}

@Preview
@Composable
fun SearchScreenPreview() {
    DictionMasterTheme {
        SearchScreen(
            {}
        )
    }
}