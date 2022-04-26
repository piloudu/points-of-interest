package com.example.pointsofinterest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pointsofinterest.R
import com.example.pointsofinterest.ui.theme.*

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
            .padding(start = 15.dp, bottom = 13.dp)
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
        Spacer(modifier = Modifier.width(25.dp))
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
            modifier = Modifier.size(32.dp),
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
                    .weight(4.5f)
                    .padding(top = 12.dp, start = 15.dp, bottom = 12.dp),
                text = text,
                color = White,
                letterSpacing = 0.69.sp,
                fontSize = 22.sp,
                fontFamily = Gothics
            )
            Row(modifier = Modifier.weight(1f)) {
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
