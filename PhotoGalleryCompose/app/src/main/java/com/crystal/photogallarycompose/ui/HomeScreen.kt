package com.crystal.photogallarycompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.crystal.photogallarycompose.R
import com.crystal.photogallarycompose.data.GalleryItem
import com.crystal.photogallarycompose.ui.theme.PhotogallaryComposeTheme

private const val TAG = "PhotoHomeScreen"

@Composable
fun HomeScreen(photos: List<GalleryItem>, modifier: Modifier = Modifier) {
    
    PhotoCellList(photos = photos)
}


@Composable
fun PhotoCellList(photos: List<GalleryItem>, modifier: Modifier = Modifier) {

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(photos) { photo ->
            PhotoCell(photo)
        }
    }

}

@Composable
fun PhotoCell(item: GalleryItem, modifier: Modifier = Modifier) {

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
//            AsyncImage(
////                model = "https://example.com/image.jpg",
//                model = item.url,
//                contentDescription = null
//            )
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(item.url)
//                    .crossfade(true)
//                    .build(),
//                placeholder = painterResource(R.drawable.ic_android),
//                contentDescription = item.title,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.clip(CircleShape)
//            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotogallaryComposeTheme {
        PhotoCell(GalleryItem(title = "test", id = "test", url="https://placebear.com/g/200/200"))
    }
}