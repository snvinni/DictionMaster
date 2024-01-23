package com.example.dictionmaster.feature.wordinfo

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.widget.Toast
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionmaster.R
import com.example.dictionmaster.core.domain.model.Word
import com.example.dictionmaster.core.ui.component.DMButton
import com.example.dictionmaster.core.ui.theme.Blue
import com.example.dictionmaster.core.ui.theme.DarkBlue
import com.example.dictionmaster.core.ui.theme.DictionMasterTheme
import com.example.dictionmaster.core.ui.theme.Gray
import com.example.dictionmaster.core.ui.theme.LightGrey
import com.example.dictionmaster.core.ui.theme.LocalDimensions
import com.example.dictionmaster.core.ui.theme.Typography
import com.example.dictionmaster.feature.NavigationRoute
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

                is NavigationRoute.NavigateToWordInfo -> Unit
            }
        }
    }

    WordInfoScreen(
        word = wordInfo,
        onAction = viewModel::onAction,
    )
}

@Composable
fun WordInfoScreen(
    word: Word,
    onAction: (Action) -> Unit,
) {
    val dimensions = LocalDimensions.current
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.padding(horizontal = dimensions.semiBig)) {
                Spacer(modifier = Modifier.height(dimensions.big))

                Text(
                    text = word.word,
                    style = Typography.titleLarge,
                    color = DarkBlue
                )

                Spacer(modifier = Modifier.height(dimensions.semiMedium))

                Row {
                    Button(
                        modifier = Modifier.size(46.dp),
                        contentPadding = PaddingValues(13.dp),
                        shape = CircleShape,
                        onClick = {
                            val mediaPlayer = MediaPlayer().apply {
                                setAudioAttributes(
                                    AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                                        .build()
                                )
                            }

                            try {
                                mediaPlayer.setDataSource(
                                    word.phonetics.first { it.audio.isNotEmpty() }.audio
                                )
                                mediaPlayer.prepareAsync()
                                mediaPlayer.setOnPreparedListener { mp ->
                                    mp.start()
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()

                                Toast.makeText(
                                    context,
                                    "Error playing audio, try again!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
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
                        text = word.phonetics.first().text,
                        modifier = Modifier
                            .padding(start = dimensions.semiMedium)
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
                    wordMeanings = word.meanings,
                )
            }

            Column(
                verticalArrangement = Arrangement.Bottom
            ) {

                Divider(
                    modifier = Modifier
                        .padding(bottom = dimensions.semiBig)
                        .fillMaxSize(),
                    color = Color.LightGray,
                    thickness = 1.dp,
                )

                Text(
                    modifier = Modifier.padding(horizontal = dimensions.semiBig),
                    text = "That’s it for “${word.word}”!",
                    style = Typography.titleLarge.copy(
                        fontSize = 24.sp
                    ),
                    color = DarkBlue
                )

                Text(
                    modifier = Modifier.padding(
                        top = dimensions.small,
                        start = dimensions.semiBig,
                        end = dimensions.semiBig,
                    ),
                    text = "Try another search now!",
                    style = Typography.bodySmall,
                    color = DarkBlue,
                )

                Spacer(modifier = Modifier.height(dimensions.medium))

                DMButton(
                    text = stringResource(R.string.word_button_text),
                    paddingValues = PaddingValues(
                        start = dimensions.semiBig,
                        end = dimensions.semiBig,
                        bottom = dimensions.semiBig
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
    wordMeanings: List<Word.Meaning>,
) {
    val dimensions = LocalDimensions.current
    val currentPosition = remember { mutableIntStateOf(1) }

    wordMeanings.onEach { meaning ->
        meaning.definitions.onEach { definition ->
            Column {
                Text(
                    modifier = Modifier.padding(bottom = dimensions.semiMedium),
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
                                color = Gray
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
                            bottom = dimensions.medium
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
            word = Word(
                id = 1,
                word = "Education",
                phonetics = listOf(
                    Word.Phonetic(
                        text = "ɛdjʊˈkeɪʃ(ə)n",
                        audio = "https://lex-audio.useremarkable.com/mp3/education_us_1_rr.mp3"
                    )
                ),
                meanings = listOf(
                    Word.Meaning(
                        partOfSpeech = "noun",
                        definitions = listOf(
                            Word.Meaning.Definition(
                                definition = "definition",
                                example = "a new system of public education"
                            ),
                        ),
                    )
                )
            )
        )
    }
}