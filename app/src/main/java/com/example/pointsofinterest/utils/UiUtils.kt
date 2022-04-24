package com.example.pointsofinterest.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.annotation.DrawableRes
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.pointsofinterest.R
import com.example.pointsofinterest.ui.theme.*

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

@Preview(widthDp = 375)
@Composable
fun TopBar(
    modifier: Modifier = Modifier.background(LightGray),
    text: String = "MADRID",
    backAction: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(start = 15.dp, end = 15.dp, bottom = 13.dp)
            .background(LightGray),
        verticalAlignment = Alignment.CenterVertically
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
            text = text,
            color = DarkGray,
            letterSpacing = 0.78.sp,
            lineHeight = 16.sp,
            fontSize = 25.sp,
            fontFamily = Gothics
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

@Preview(widthDp = 375)
@Composable
fun TopDescriptionBar(
    modifier: Modifier = Modifier.background(DarkGray1),
    text: String = "LATINA - ÓPERA",
    pois_count: Int = 118,
    squareButtonColor: Color = Orange
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row(
            modifier = modifier.width(325.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp, start = 15.dp, bottom = 12.dp),
                text = text,
                color = White,
                letterSpacing = 0.69.sp,
                fontSize = 22.sp,
                fontFamily = Gothics
            )
            Spacer(modifier = Modifier.width(65.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_map_marker),
                contentDescription = "map marker"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier.width(42.dp),
                text = pois_count.toString(),
                maxLines = 1,
                fontSize = 20.sp,
                letterSpacing = 0.63.sp,
                color = White,
                fontFamily = Gothics
            )
            Spacer(modifier = Modifier.width(18.dp))
        }
        Box(
            modifier = Modifier
                .width(50.dp)
                .fillMaxSize()
                .background(squareButtonColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dots_lines_clipart),
                contentDescription = "square list button"
            )
        }
    }
}

@Preview(widthDp = 375)
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    text: String = "MOSTRAR EN LISTADO",
    @DrawableRes resId: Int = R.drawable.ic_lines_clipart
) {
    Row(
        modifier
            .fillMaxWidth()
            .background(Gray)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .width(190.dp)
                .padding(start = 21.dp, top = 15.dp, bottom = 12.dp),
            text = text,
            color = LightGray,
            letterSpacing = 0.38.sp,
            fontSize = 20.sp,
            fontFamily = Gothics
        )
        Spacer(Modifier.width(138.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_lines_clipart),
            contentDescription = "list menu"
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