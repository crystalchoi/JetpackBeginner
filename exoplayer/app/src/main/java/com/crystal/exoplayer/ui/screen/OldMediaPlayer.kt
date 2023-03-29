package com.crystal.exoplayer.ui.screen

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.crystal.exoplayer.R

@Composable
fun OldMediaPlayer(myUri: String, context: Context = LocalContext.current) {

    val sampleSong: MediaPlayer by remember {
        mutableStateOf(
//            MediaPlayer.create(context, R.raw.realenglish001) // your audio file
            MediaPlayer().apply {
//                    setAudioStreamType(AudioManager.STREAM_MUSIC)
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(context, myUri.toUri())
                prepareAsync()
                seekTo(60000)   // skip the intro-music
            }

        )
    } // track the playing state
    var isPlaying by remember {
        mutableStateOf(false)
    }

    Card( // Icon and button holder
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        // this is similar to the MusicItem properties
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            // Align items end to end
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_audiotrack),
                    contentDescription = "audio icon"
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(all = 8.dp)
                ) {
                    Text(text = "Sample Song")
                    Text(
                        text = "Artist",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            // Add control buttons
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_prev),
                    contentDescription = "back button"
                )
                // check state and set/update the icon
                IconButton(onClick = {
                    if (isPlaying) {
                        sampleSong.pause()
                    } else {
                        sampleSong.start()
                    }
                    isPlaying = !isPlaying
                }) {
                    Icon(
                        painter = painterResource(
                            id =
                            if (isPlaying) R.drawable.ic_pause
                            else R.drawable.ic_play
                        ),
                        contentDescription = "play/pause button"
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "next button"
                )
            }
        }
    }
}


