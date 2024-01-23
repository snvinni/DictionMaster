package com.example.dictionmaster.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import com.example.dictionmaster.R
import com.example.dictionmaster.core.ui.theme.LocalDimensions

@Composable
fun DMIcon(
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current

    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .shadow(
                    elevation = dimensions.default,
                    shape = RoundedCornerShape(25),
                )
                .align(Alignment.CenterHorizontally),
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