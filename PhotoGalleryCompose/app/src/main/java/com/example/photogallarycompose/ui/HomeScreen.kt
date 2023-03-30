package com.example.photogallarycompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.photogallarycompose.R
import com.example.photogallarycompose.ui.theme.PhotogallaryComposeTheme

private const val TAG = "PhotoHomeScreen"

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    
    
}


@Composable
fun PhotoCellList(/*photos: List<Photo>, */ modifier: Modifier = Modifier) {

//    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
//        items(photos) { photo ->
//            PhotoCell(photo)
//        }
//    }

}

@Composable
fun PhotoCell(modifier: Modifier = Modifier) {




    Column() {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
//            .background(Color.Cyan)
//                .weight(1f, fill = true)
                .clickable { }
                .padding(vertical = 4.dp, horizontal = 4.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_android),
                contentDescription = "anything",
                contentScale = ContentScale.Fit,
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotogallaryComposeTheme {
        PhotoCell()
    }
}