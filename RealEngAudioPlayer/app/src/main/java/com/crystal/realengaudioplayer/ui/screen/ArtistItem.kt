package com.crystal.realengaudioplayer.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crystal.realengaudioplayer.Greeting
import com.crystal.realengaudioplayer.R
import com.crystal.realengaudioplayer.ui.theme.RealEngAudioPlayerTheme

@Composable
fun ArtistItem() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image( // cast the Image import to match this name // ArtistImage
            painter = painterResource(id = R.drawable.ic_baseline_music_note_24),
            modifier = Modifier
                .padding(all = 8.dp)
                .size(60.dp)
                .clip(CircleShape),
            contentScale = Crop,
            contentDescription = "artist image"
        )
        Text(
            text = "Juice Wrld"
        )
    }
}

@Composable
fun MusicItem() {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_audiotrack_24),
            contentDescription = "audio icon"
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Text(text = "Song title")
            Text(
                text = "Song artist",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RealEngAudioPlayerTheme {
        ArtistItem()
        MusicItem()
    }
}