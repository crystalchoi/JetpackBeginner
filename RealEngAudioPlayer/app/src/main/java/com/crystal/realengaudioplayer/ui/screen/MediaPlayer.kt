package com.crystal.realengaudioplayer.ui.screen

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
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crystal.realengaudioplayer.Greeting
import com.crystal.realengaudioplayer.R
import com.crystal.realengaudioplayer.ui.theme.RealEngAudioPlayerTheme


private const val sample_url = "https://v1.wisdomhouse.co.kr/mp3/realenglish/realenglish001.mp3"
//private const val sample_url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
//  https://developer.android.com/codelabs/exoplayer-intro?hl=ko#2  코드랩의 ExoPlayer 참고해서... Exo버전 만들기
@Composable
fun PlaySampleAudio(context: Context) {

    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    // remember {    mutableStateOf(MediaPlayer.create(context, R.raw.realenglish001))
    // } // track the playing state



    var isPlaying by remember { mutableStateOf(false) }

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
                    painter = painterResource(id = R.drawable.ic_baseline_audiotrack_24),
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
                    if (mediaPlayer == null) {
                        mediaPlayer = MediaPlayer()
                    }
                    val sampleSong = mediaPlayer as MediaPlayer
                    if (isPlaying) {
                        sampleSong.pause()
                        sampleSong.stop()
                        sampleSong.reset()
                        sampleSong.release()
                        mediaPlayer = null
                    } else {
                        var audioUrl = sample_url

                            sampleSong.setAudioAttributes(
                                AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .build())
//                          mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC) deprecated error ~~
                        try {
                            sampleSong.setDataSource(audioUrl)
                            sampleSong.prepareAsync()
                            sampleSong.start()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    isPlaying = !isPlaying
                }) {
                    Icon(
                        painter = painterResource(
                            id =
                            if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
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


