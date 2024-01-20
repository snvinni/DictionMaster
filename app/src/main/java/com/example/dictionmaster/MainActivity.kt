package com.example.dictionmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dictionmaster.ui.theme.DictionMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionMasterTheme {
                // A surface container using the 'background' color from the theme
                InitialLoadingScreen()
            }
        }
    }
}