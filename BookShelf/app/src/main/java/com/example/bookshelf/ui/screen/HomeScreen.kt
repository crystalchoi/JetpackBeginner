package com.example.bookshelf.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.model.google.search.GoogleBook
import com.example.bookshelf.R
import com.example.bookshelf.model.google.volume.ImageLinks
import com.example.bookshelf.model.google.search.Item
import com.example.bookshelf.ui.theme.BookShelfTheme


@Composable
fun HomeScreen(
    uiState: BookUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is BookUiState.Success ->     ResultScreen(uiState.books, uiState.imageList, modifier)
        is BookUiState.Error ->      ErrorScreen(retryAction, modifier)
        is BookUiState.Loading ->     LoadingScreen(modifier)
    }

}

/**
 * The home screen displaying result of fetching photos.
 */
@Composable
fun ResultScreen(books: GoogleBook,
                 imageList: List<ImageLinks>,
                 modifier: Modifier = Modifier) {

    LazyVerticalGrid(columns =  GridCells.Adaptive(minSize = 150.dp)
        ,  modifier = modifier.fillMaxWidth()
        , contentPadding = PaddingValues(4.dp)
    ) {
        items(books.items.size) { index ->
            BookCard(books.items[index], imageList[index])
        }
    }
}


@Composable
fun BookCard(item: Item, imageLinks: ImageLinks, modifier: Modifier = Modifier) {
    var showDescription by remember { mutableStateOf(false) }

    Card(modifier = modifier.fillMaxHeight()
        , elevation = 8.dp
        , shape = RoundedCornerShape(8.dp)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally
            ,  modifier = Modifier.fillMaxWidth()
        ) {

            Text(text = item.volumeInfo.title
                , style = MaterialTheme.typography.h6
                , fontWeight = FontWeight.Bold
                , textAlign = TextAlign.Center
//                , fontSize = 24.sp
                , modifier = Modifier
                    .padding(top = 1.dp)
                    .clickable {
                        showDescription = !showDescription
                    })

//            val imageSrc = if (imageLinks.smallThumbnail.isNotEmpty()) {
//                imageLinks.smallThumbnail
//            } else if (imageLinks.thumbnail.isNotEmpty()) {
//                imageLinks.thumbnail
//            } else {
//                ""
//            }

//            if (imageSrc == "") {
//                Image(painter = painterResource(R.drawable.ic_baseline_broken_image_24)
//                    , contentDescription = null
//                    , modifier = Modifier.width(40.dp).height(40.dp))
//            } else {
//                imageSrc.replace(
//                    oldValue = "http://",
//                    newValue = "https://"
//                )
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(imageLinks.smallThumbnail.replace(
                            oldValue = "http://",
                            newValue = "https://"
                        ))
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.book_smallThumbnail),
                    contentScale = ContentScale.FillWidth,
                    //                contentScale = ContentScale.Crop,
                    //                contentScale = ContentScale.FillBounds,
                    error = painterResource(R.drawable.ic_baseline_broken_image_24),
                    //                placeholder = painterResource(R.drawable.ic_baseline_downloading_24),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showDescription = !showDescription
                        }
                )
//            }
        }

    }

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {

    }
}


@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {

    }
}
@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    BookShelfTheme {
//        ResultScreen(GoogleBook(kind = "kind", totalItems = 200, items = listOf(null)))
    }
}
