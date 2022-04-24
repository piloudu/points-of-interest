package com.example.pointsofinterest.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pointsofinterest.R
import com.example.pointsofinterest.ui.theme.DarkGray
import com.example.pointsofinterest.ui.theme.LightGray

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = Color.Black
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(screenHeight / 3))
        CircularProgressIndicator(
            modifier = Modifier
                .width(screenWidth / 2)
                .height(screenHeight / 3),
            color = color
        )
        Spacer(Modifier.height(screenHeight / 3))
    }
}

@Preview
@Composable
fun TopBar(
    modifier: Modifier = Modifier.background(LightGray),
    backAction: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(start = 15.dp, end = 15.dp, bottom = 13.dp)
            .background(LightGray)
    ) {
        Image(
            modifier = Modifier.clickable {
                backAction()
            },
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = "back arrow"
        )
        Spacer(modifier = Modifier.width(32.dp))
        Text(
            modifier = Modifier
                .width(199.dp),
            text = "MADRID",
            color = DarkGray,
            letterSpacing = 0.78.sp,
            lineHeight = 16.sp,
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = "calendar"
        )
        Spacer(modifier = Modifier.width(5.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "arrow"
        )
    }
}

/**
 * Used to block the landscape view in the LoginScreen
 */
@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

private fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}