package com.example.bookshelf.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bookshelf.R
import com.example.bookshelf.ui.screen.*


@Composable
fun BookShelfApp(viewModel: BookViewModel, modifier: Modifier = Modifier) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Surface(
                color = MaterialTheme.colors.primary
            ) {
                ExpandableSearchView(
                    searchDisplay = "",
                    onSearchDisplayChanged = {  viewModel.searchBooks(it) },
//                    expandedInitially = true,
                    onSearchDisplayClosed = {
                        Log.i("BookShelfApp", "onSearchDisplayClosed")
                    }

                )
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {


            HomeScreen(
                uiState = viewModel.uiState, retryAction = viewModel::getBooks  // {}

            )
        }
    }
}