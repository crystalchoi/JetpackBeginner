package com.example.amphibians.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.amphibians.network.AnimalData
import com.example.amphibians.ui.theme.AmphibiansTheme
import com.example.amphibians.ui.screen.AmphibiansUiState
import com.example.amphibians.R.drawable.loading_img
import com.example.amphibians.R.*


@Composable
fun HomeScreen(uiState: AmphibiansUiState
               , retryAction: () -> Unit
               ,  modifier: Modifier = Modifier
) {
    when (uiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier)
        is AmphibiansUiState.Error -> ErrorScreen(retryAction, modifier)
        is AmphibiansUiState.Success -> AmphibiansListScreen(uiState.infos, modifier)
//            AnimalCard(uiState.infos[0])

    }
}



@Composable
fun AmphibiansListScreen(animalDatas: List<AnimalData>, modifier: Modifier = Modifier) {
    LazyColumn(
//        columns =  GridCells.Adaptive(minSize = 250.dp)
        modifier = modifier.fillMaxWidth()
        , verticalArrangement = Arrangement.spacedBy(16.dp)
        , contentPadding = PaddingValues(4.dp)
    ) {
        items(items = animalDatas, key = { data -> data.name }) { data ->
            AnimalCard(data)
        }
    }
}


@Composable
fun AmphibiansGridScreen(animalDatas: List<AnimalData>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns =  GridCells.Adaptive(minSize = 250.dp)
        ,  modifier = modifier.fillMaxWidth()
        , contentPadding = PaddingValues(4.dp)
    ) {
        items(items = animalDatas, key = { data -> data.name }) { data ->
            AnimalCard(data)
        }
    }
}


@Composable
fun AnimalCard(animalData : AnimalData, modifier: Modifier = Modifier) {


    Card(modifier = modifier.fillMaxHeight()
//        .fillMaxSize().padding(all = 8.dp)
//       .aspectRatio(0.8f)
        , elevation = 8.dp
        , shape = RoundedCornerShape(8.dp)
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally
            ,  modifier = Modifier.fillMaxWidth()
        ){

            var expandedDescription by remember { mutableStateOf(true) }

            Spacer(Modifier.height(12.dp))
            Text(text = "${animalData.name} (${animalData.type})"
                , style = MaterialTheme.typography.h6
                , fontWeight = FontWeight.Bold
                , textAlign = TextAlign.Center
//                , fontSize = 24.sp
                , modifier = Modifier.padding(top = 16.dp).clickable {
                    expandedDescription = !expandedDescription
            })
//            Spacer(Modifier.height(8.dp))
            if (expandedDescription) {
                Text(
                    text = animalData.description
                    , style = MaterialTheme.typography.body1
//                     , fontSize = 18.sp
//                    , modifier = Modifier.padding(all = 8.dp).fillMaxWidth()
                    , modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                    , textAlign = TextAlign.Justify
                )
//                Spacer(Modifier.height(8.dp))
            }
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(animalData.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(com.example.amphibians.R.string.amphibians_photo),
                contentScale = ContentScale.FillWidth,
//                contentScale = ContentScale.Crop,
//                contentScale = ContentScale.FillBounds,
                error = painterResource(com.example.amphibians.R.drawable.ic_baseline_broken_image_24),
//                placeholder = painterResource(com.example.amphibians.R.drawable.ic_baseline_downloading_24),
                modifier = Modifier.fillMaxWidth().clickable {
                    expandedDescription = !expandedDescription
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
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = com.example.amphibians.R.drawable.loading_img),
            contentDescription = stringResource(id = com.example.amphibians.R.string.loading)
        )
    }
}


@Composable
fun ErrorScreen(retryAction: () -> kotlin.Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(com.example.amphibians.R.string.loading_failed))
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = retryAction) {
            Text(stringResource(com.example.amphibians.R.string.retry))
//            Text(stringResource(com.example.amphibians.R.string.retry))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AnimalCardPreview() {
    AmphibiansTheme {
        AnimalCard(AnimalData(name = "Great Basin Spadefoot"
            , type = "Toad"
            , description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots."
            , imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
        ))
    }
}

