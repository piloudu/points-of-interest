package com.example.pointsofinterest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pointsofinterest.data_model.DataModel
import com.example.pointsofinterest.ui.theme.LightBlack
import com.example.pointsofinterest.ui.theme.RobotoLight

@Preview
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    dataModel: DataModel = DataModel.initial()
) {
    LazyColumn {
        item {

        }
    }
}

@Preview
@Composable
private fun ListHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(LightBlack),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Ordenar: ",
            letterSpacing = 0.3.sp,
            fontSize = 16.sp,
            fontFamily = RobotoLight
        )
    }
}