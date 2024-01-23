package com.example.dictionmaster.feature.wordinfo

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WordInfoRoute(
    viewModel: WordInfoViewModel = hiltViewModel(),
    wordInfoId: Int
) {
    val wordInfo = viewModel.getCurrentWord(wordInfoId)

    Surface {
        Text(text = wordInfo.word)
    }
}

@Composable
fun WordInfoScreen() {

}