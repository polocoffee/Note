package com.banklannister.notes.core.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.banklannister.notes.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenAnimation(
    onNavigateToNoteList: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(5000)

        onNavigateToNoteList()
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_screen))

    val progress by animateLottieCompositionAsState(
        isPlaying = true,
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 1f
    )

    //Display Animation
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Notes",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 30.sp
        )

        Text(
            text = "Organize your ideas",
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )


    }


}