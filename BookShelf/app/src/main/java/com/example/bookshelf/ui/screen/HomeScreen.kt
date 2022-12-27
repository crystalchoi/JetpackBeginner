package com.example.bookshelf.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(books.kind, fontSize = 24.sp)
        Text(books.totalItems.toString(), fontSize = 24.sp)
        BookCard(books.items[0], imageList[0])
    }
}


@Composable
fun BookCard(item: Item, imageLinks: ImageLinks, modifier: Modifier = Modifier) {
    //GoogleBook.items[0].volumeInfo.title/imageLinks.smallThumbnail/thumbnail
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
                , modifier = Modifier.padding(top = 1.dp).clickable {
                    showDescription = !showDescription
                })

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageLinks.smallThumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.book_smallThumbnail),
                contentScale = ContentScale.FillWidth,
    //                contentScale = ContentScale.Crop,
    //                contentScale = ContentScale.FillBounds,
                error = painterResource(R.drawable.ic_baseline_broken_image_24),
    //                placeholder = painterResource(R.drawable.ic_baseline_downloading_24),
                modifier = Modifier.fillMaxWidth().clickable {
                    showDescription = !showDescription
                }
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageLinks.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.book_smallThumbnail),
                contentScale = ContentScale.FillWidth,
                //                contentScale = ContentScale.Crop,
                //                contentScale = ContentScale.FillBounds,
                error = painterResource(R.drawable.ic_baseline_broken_image_24),
                //                placeholder = painterResource(R.drawable.ic_baseline_downloading_24),
                modifier = Modifier.fillMaxWidth().clickable {
                    showDescription = !showDescription
                }
            )
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
