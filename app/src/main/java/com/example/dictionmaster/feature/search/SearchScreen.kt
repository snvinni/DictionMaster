package com.example.dictionmaster.feature.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionmaster.R
import com.example.dictionmaster.core.ui.component.DMButton
import com.example.dictionmaster.core.ui.component.ObserverWithLifecycle
import com.example.dictionmaster.core.ui.theme.DarkBlue
import com.example.dictionmaster.core.ui.theme.DictionMasterTheme
import com.example.dictionmaster.core.ui.theme.Grey
import com.example.dictionmaster.core.ui.theme.LocalDimensions
import com.example.dictionmaster.core.ui.theme.Typography

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateToWordInfo: (Int) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val isLoading = remember { mutableStateOf(false) }

    ObserverWithLifecycle(viewModel.uiState) { uiState ->
        when (uiState) {
            UiState.Loading -> {
                isLoading.value = true
            }

            is UiState.Success -> {
                onNavigateToWordInfo(uiState.wordInfo.id)
            }

            else -> {
                isLoading.value = false

                if (uiState is UiState.Error) {
                    snackbarHostState.showSnackbar(
                        message = uiState.message,
                    )
                }
            }
        }
    }

    SearchScreen(
        word = viewModel.word.collectAsState().value,
        onAction = viewModel::onAction,
        isLoading = isLoading.value,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
fun SearchScreen(
    word: String,
    onAction: (Action) -> Unit,
    isLoading: Boolean,
    snackbarHostState: SnackbarHostState
) {
    val dimensions = LocalDimensions.current

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            DMButton(
                text = if (isLoading) {
                    stringResource(R.string.search_button_loading)
                } else {
                    stringResource(R.string.search_button)
                },
                onClick = { onAction(Action.OnSearch) },
                isEnabled = word.isNotEmpty(),
                paddingValues = PaddingValues(
                    dimensions.large
                ),
            )
        },
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            )
        },
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(44.dp))

            Row(
                modifier = Modifier
                    .background(Color.White)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_english),
                    contentDescription = null,
                    modifier = Modifier.padding(
                        dimensions.small,
                    )
                )

                Text(
                    text = stringResource(R.string.search_language),
                    modifier = Modifier
                        .padding(start = dimensions.small)
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
                word = word,
                onWordChange = { onAction(Action.OnWordChange(it)) },
            )
        }
    }
}

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

@Preview
@Composable
fun SearchScreenPreview() {
    DictionMasterTheme {
        SearchScreen(
            word = "",
            onAction = {},
            isLoading = false,
            snackbarHostState = SnackbarHostState(),
        )
    }
}