package com.example.newsapp.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import com.example.newsapp.R
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error? = null) {
    var message by remember {
        mutableStateOf("You have not saved news so far!")
    }
    var icon by remember {
        mutableStateOf(R.drawable.ic_search)
    }

    if (error != null) {
        message = parseErrorMessage(error)
        icon = R.drawable.ic_time
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.3f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                alpha = alphaAnimation,
                colorFilter = ColorFilter.tint(androidx.compose.ui.graphics.Color.Gray)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = message, fontSize = 18.sp)
        }
    }
}

fun parseErrorMessage(error: LoadState.Error?): String {
    return when (error?.error) {
        is SocketTimeoutException -> "Server Unavailable!"
        is ConnectException -> "Internet Unavailable."
        else -> "Unknown Error."
    }
}

@Composable
fun EmptyContent(alphaAnim: Float, message: String, iconId: Int) {
    val alphaAnimation by animateFloatAsState(
        targetValue = alphaAnim,
        animationSpec = tween(durationMillis = 1000)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .alpha(alphaAnimation),
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                fontSize = 18.sp
            ),
            modifier = Modifier.alpha(alphaAnimation)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyContentPreview() {
    EmptyContent(
        alphaAnim = 1f,
        message = "No content available.",
        iconId = R.drawable.ic_search
    )
}
