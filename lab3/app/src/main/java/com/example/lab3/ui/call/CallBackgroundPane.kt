package com.example.lab3.ui.call

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.lab3.R


@Composable
fun CallBackgroundPane(photo: Int, modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .then(modifier)) {
        Image(
            painter = painterResource(id = photo),
            contentDescription = stringResource(R.string.call_callee_photo_cont_desc),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        val overlayBrush = Brush.verticalGradient(
            colorStops = arrayOf(
                0.3f to Color.Transparent,
                0.8f to MaterialTheme.colorScheme.tertiary
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(overlayBrush)
        )
    }
}