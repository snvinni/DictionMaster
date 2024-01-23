package com.example.dictionmaster.feature.subscribe

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dictionmaster.R
import com.example.dictionmaster.core.ui.component.DMButton
import com.example.dictionmaster.core.ui.component.DMIcon
import com.example.dictionmaster.core.ui.theme.Blue
import com.example.dictionmaster.core.ui.theme.DarkBlue
import com.example.dictionmaster.core.ui.theme.DictionMasterTheme
import com.example.dictionmaster.core.ui.theme.LocalDimensions
import com.example.dictionmaster.core.ui.theme.Typography

@Composable
fun SubscribeScreen() {
    val dimensions = LocalDimensions.current
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(id = R.drawable.subscribe),
                contentDescription = null,
            )

            DMIcon(
                modifier = Modifier
                    .offset(y = (-50).dp)
                    .align(Alignment.CenterHorizontally)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimensions.semiBig)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = DarkBlue
                            )
                        ) {
                            append(stringResource(id = R.string.subscribe_text) + " ")
                        }
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Blue
                            )
                        ) {
                            append(stringResource(id = R.string.subscribe_text_unlimited) + " ")
                        }
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = DarkBlue
                            )
                        ) {
                            append(stringResource(id = R.string.subscribe_text_full_access) + " ")
                        }
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Blue
                            )
                        ) {
                            append(stringResource(id = R.string.subscribe_text_full_all_features))
                        }
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = DarkBlue
                            )
                        ) {
                            append(".")
                        }
                    },
                    style = Typography.bodyMedium,
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = dimensions.medium),
                    textAlign = TextAlign.Center,
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = DarkBlue
                            )
                        ) {
                            append(stringResource(id = R.string.subscribe_text_seven_days))
                        }
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Light,
                                color = DarkBlue
                            )
                        ) {
                            append(
                                stringResource(
                                    id = R.string.subscribe_text_seven_days_then_only
                                ) + " "
                            )
                        }
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = DarkBlue
                            )
                        ) {
                            append(stringResource(id = R.string.subscribe_text_price) + " ")
                        }
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Light,
                                color = DarkBlue
                            )
                        ) {
                            append(stringResource(id = R.string.subscribe_text_price_per_year))
                        }
                    },
                    style = Typography.bodySmall,
                )

                Text(
                    text = stringResource(id = R.string.subscribe_text_cancel),
                    style = Typography.bodySmall,
                    color = DarkBlue,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )

                Spacer(modifier = Modifier.weight(1f))

                DMButton(
                    text = stringResource(id = R.string.subscribe_button),
                    onClick = {
                        Toast.makeText(
                            context,
                            "Thank you for your attention!",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    isEnabled = true,
                    paddingValues = PaddingValues(
                        top = dimensions.medium,
                    ),
                )

                Spacer(modifier = Modifier.height(dimensions.big))
            }
        }
    }
}

@Preview
@Composable
fun PreviewSubscribeScreen() {
    DictionMasterTheme {
        SubscribeScreen()
    }
}