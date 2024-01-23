package com.example.dictionmaster.feature.wordinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionmaster.R
import com.example.dictionmaster.core.domain.model.WordInfo
import com.example.dictionmaster.core.ui.component.DMButton
import com.example.dictionmaster.core.ui.theme.Blue
import com.example.dictionmaster.core.ui.theme.DarkBlue
import com.example.dictionmaster.core.ui.theme.DictionMasterTheme
import com.example.dictionmaster.core.ui.theme.Grey
import com.example.dictionmaster.core.ui.theme.LightGrey
import com.example.dictionmaster.core.ui.theme.Typography
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WordInfoRoute(
    viewModel: WordInfoViewModel = hiltViewModel(),
    wordInfoId: Int,
    onNavigate: (NavigationRoute) -> Unit,
) {
    val wordInfo = viewModel.getCurrentWord(wordInfoId)

    LaunchedEffect(key1 = Unit) {
        viewModel.eventsFlow.collectLatest { value ->
            when (value) {
                NavigationRoute.NavigateToSearch -> onNavigate(NavigationRoute.NavigateToSearch)

                NavigationRoute.NavigateToSubscribe -> onNavigate(NavigationRoute.NavigateToSubscribe)
            }
        }
    }

    WordInfoScreen(
        wordInfo = wordInfo,
        onAction = viewModel::onAction,
    )
}

@Composable
fun WordInfoScreen(
    wordInfo: WordInfo,
    onAction: (Action) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                Spacer(modifier = Modifier.height(38.dp))

                Text(
                    text = wordInfo.word,
                    style = Typography.titleLarge,
                    color = DarkBlue
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    Button(
                        modifier = Modifier.size(46.dp),
                        contentPadding = PaddingValues(13.dp),
                        shape = CircleShape,
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue,
                            disabledContainerColor = Color.Gray,
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_sound),
                            contentDescription = null
                        )
                    }

                    Text(
                        text = wordInfo.phonetics.first().text,
                        modifier = Modifier
                            .padding(start = 11.dp)
                            .align(Alignment.CenterVertically),
                        style = Typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                        color = LightGrey,
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                WordMeanings(
                    wordMeanings = wordInfo.meanings,
                )
            }

            Divider(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxSize(),
                color = Color.LightGray,
                thickness = 1.dp,
            )

            Column(
                modifier = Modifier.padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "That’s it for “${wordInfo.word}”!",
                    style = Typography.titleLarge.copy(
                        fontSize = 24.sp
                    ),
                    color = DarkBlue
                )

                Text(
                    modifier = Modifier.padding(
                        top = 8.dp
                    ),
                    text = "Try another search now!",
                    style = Typography.bodySmall,
                    color = DarkBlue,
                )

                Spacer(modifier = Modifier.height(20.dp))

                DMButton(
                    text = "NEW SEARCH",
                    paddingValues = PaddingValues(
                        bottom = 30.dp
                    ),
                    onClick = {
                        onAction(Action.OnNewSearch(false))
                    },
                )
            }
        }
    }
}

@Composable
fun WordMeanings(
    wordMeanings: List<WordInfo.Meaning>,
) {
    val currentPosition = remember { mutableIntStateOf(1) }

    wordMeanings.onEach { meaning ->
        meaning.definitions.onEach { definition ->
            Column {
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = DarkBlue
                            )
                        ) {
                            append("${currentPosition.intValue}) ")
                        }
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Grey
                            )
                        ) {
                            append("[${meaning.partOfSpeech}]")
                        }
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = DarkBlue
                            )
                        ) {
                            append(" ${definition.definition}")
                        }
                    },
                    style = Typography.bodySmall,
                )

                if (definition.example.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(
                            bottom = 22.dp
                        ),
                        text = "• ${definition.example}",
                        style = Typography.bodySmall,
                        color = DarkBlue,
                    )
                }
            }
            currentPosition.intValue++
        }
    }
}

@Preview
@Composable
fun WordInfoScreenPreview() {
    DictionMasterTheme {
        WordInfoScreen(
            onAction = {},
            wordInfo = WordInfo(
                id = 1,
                word = "Education",
                phonetics = listOf(
                    WordInfo.Phonetic(
                        text = "ɛdjʊˈkeɪʃ(ə)n",
                        audio = "https://lex-audio.useremarkable.com/mp3/education_us_1_rr.mp3"
                    )
                ),
                meanings = listOf(
                    WordInfo.Meaning(
                        partOfSpeech = "noun",
                        definitions = listOf(
                            WordInfo.Meaning.Definition(
                                definition = "the process of receiving or giving systematic instruction, especially at a school or university.",
                                example = "a new system of public education"
                            ),
                        ),
                    )
                )
            )
        )
    }
}