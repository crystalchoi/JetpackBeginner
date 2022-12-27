package com.example.bookshelf.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.bookshelf.model.google.GoogleBook
import com.example.bookshelf.ui.theme.BookShelfTheme


@Composable
fun HomeScreen(
    uiState: BookUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is BookUiState.Success ->     ResultScreen(uiState.books, modifier)
        is BookUiState.Error ->      ErrorScreen(retryAction, modifier)
        is BookUiState.Loading ->     LoadingScreen(modifier)
    }

}

/**
 * The home screen displaying result of fetching photos.
 */
@Composable
fun ResultScreen(books: GoogleBook, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(books.kind, fontSize = 24.sp)
        Text(books.totalItems.toString(), fontSize = 24.sp)
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
