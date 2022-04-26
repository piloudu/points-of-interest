package com.example.pointsofinterest.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pointsofinterest.R
import com.example.pointsofinterest.data_model.DataModel
import com.example.pointsofinterest.data_model.Poi
import com.example.pointsofinterest.ui.theme.*

@Preview(showBackground = true)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    dataModel: DataModel = DataModel.initial()
) {
    LazyColumn {
        item {
            ListHeader()
        }
        itemsIndexed(dataModel.pois) { _: Int, poi: Poi ->
            with(poi) {
                ListElement(
                    imageId = id,
                    image = image,
                    likesCount = likesCount,
                    text = name
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(LightBlack)
            .padding(start = 15.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Ordenar: ",
            letterSpacing = 0.3.sp,
            fontSize = 16.sp,
            fontFamily = RobotoLight,
            color = LightGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListElement(
    modifier: Modifier = Modifier,
    imageId: Int = 0,
    image: Bitmap? = null,
    likesCount: Int = 0,
    text: String = "Here the POI name"
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .height(70.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .width(70.dp)
                .height(70.dp)
        ) {
            image?.let {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = image.asImageBitmap(),
                    contentDescription = imageId.toString()
                )
            }
        }
        Spacer(Modifier.width(15.dp))
        Text(
            modifier = Modifier.weight(3f),
            text = text,
            letterSpacing = 0.26.sp,
            fontSize = 14.sp,
            fontFamily = RobotoMedium,
            color = DarkGray1
        )
        Row(
            Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = likesCount.toString(),
                letterSpacing = 0.24.sp,
                fontSize = 13.sp,
                fontFamily = RobotoMedium,
                color = LightGray1
            )
            Spacer(Modifier.width(3.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "heart"
            )
            Spacer(Modifier.width(15.dp))
        }
    }
}