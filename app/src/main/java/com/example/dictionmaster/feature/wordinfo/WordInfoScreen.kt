package com.example.dictionmaster.feature.wordinfo

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WordInfoRoute(
    wordInfo: String
) {
    Surface {
        Text(text = wordInfo)
    }
}

@Composable
fun WordInfoScreen() {

}