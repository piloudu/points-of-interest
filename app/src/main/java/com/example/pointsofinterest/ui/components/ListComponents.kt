package com.example.pointsofinterest.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pointsofinterest.R
import com.example.pointsofinterest.ui.theme.*
import com.example.pointsofinterest.utils.SortingOption
import com.example.pointsofinterest.view_model.MainActivityUserIntent
import com.example.pointsofinterest.view_model.MainViewModelInstance

@Preview(showBackground = true)
@Composable
fun ListHeader(
    modifier: Modifier = Modifier,
    selectedSortingOption: SortingOption = SortingOption.LIKES
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
        DropdownPanel(selectedSortingOption = selectedSortingOption)
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

@Composable
fun DropdownPanel(
    modifier: Modifier = Modifier,
    selectedSortingOption: SortingOption
) {
    var expanded: Boolean by remember { mutableStateOf(false) }

    Row(
        Modifier
            .clickable {
                expanded = !expanded
            },
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = selectedSortingOption.displayName,
            color = LightGray,
            fontSize = 16.sp,
            fontFamily = RobotoMedium,
            letterSpacing = 0.3.sp
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .wrapContentHeight()
        ) {
            SortingOption.values().forEach { sortingOption ->
                val selected = selectedSortingOption == sortingOption
                val color = if (selected) Orange else LightGray

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        MainViewModelInstance.sendIntent(
                            MainActivityUserIntent.SelectSortingOption(
                                sortingOption
                            )
                        )
                    }) {
                    Text(
                        text = sortingOption.displayName,
                        color = color,
                        fontSize = 16.sp,
                        fontFamily = RobotoMedium,
                        letterSpacing = 0.3.sp
                    )
                }
            }
        }
    }
}