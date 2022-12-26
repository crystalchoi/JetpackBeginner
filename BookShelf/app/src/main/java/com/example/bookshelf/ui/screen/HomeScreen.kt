package com.example.bookshelf.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookshelf.R
import com.example.bookshelf.ui.theme.BookShelfTheme


@Composable
fun HomeScreen(
    marsUiState: String,
    modifier: Modifier = Modifier
) {
    ResultScreen(marsUiState, modifier)
}

/**
 * The home screen displaying result of fetching photos.
 */
@Composable
fun ResultScreen(marsUiState: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(marsUiState)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    BookShelfTheme {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}
