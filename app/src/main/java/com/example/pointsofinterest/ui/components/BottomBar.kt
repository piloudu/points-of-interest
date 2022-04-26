package com.example.pointsofinterest.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pointsofinterest.R
import com.example.pointsofinterest.ui.theme.Gothics
import com.example.pointsofinterest.ui.theme.Gray
import com.example.pointsofinterest.ui.theme.LightGray

@Preview(widthDp = 375)
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    text: String = "MOSTRAR EN LISTADO",
    @DrawableRes resId: Int = R.drawable.ic_lines_clipart,
    goBackAction: () -> Unit = {}
) {
    Row(
        modifier
            .fillMaxWidth()
            .background(Gray)
            .height(50.dp)
            .clickable { goBackAction() },
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
        Spacer(Modifier.width(132.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_lines_clipart),
            contentDescription = "list menu"
        )
    }
}