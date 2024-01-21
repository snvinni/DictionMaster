package com.example.dictionmaster.feature.splash

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dictionmaster.R
import com.example.dictionmaster.core.ui.theme.DarkBlue
import com.example.dictionmaster.core.ui.theme.DictionMasterTheme
import com.example.dictionmaster.core.ui.theme.LocalDimensions
import com.example.dictionmaster.core.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    startActivity: () -> Unit
) {
    LaunchedEffect(true) {
        delay(2000)
        startActivity()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            InitialLoadingIcon(
                modifier = Modifier
                    .align(Alignment.Center)
            )

            Text(
                text = "by Vinicius",
                style = Typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 41.dp)
                    .align(Alignment.BottomCenter),
                color = DarkBlue,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun InitialLoadingIcon(
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )

    Column(
        modifier = modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
            transformOrigin = TransformOrigin.Center
        }
    ) {
        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_app),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(dimensions.medium))

        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_app_tittle),
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InitialLoadingScreenPreview() {
    DictionMasterTheme {
        SplashScreen(
            startActivity = {}
        )
    }
}