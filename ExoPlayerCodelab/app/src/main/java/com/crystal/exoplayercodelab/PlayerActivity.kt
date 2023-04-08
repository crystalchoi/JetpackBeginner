package com.crystal.exoplayercodelab

import android.annotation.SuppressLint
import android.media.session.MediaSession
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.datasource.HttpDataSource.HttpDataSourceException
import androidx.media3.datasource.HttpDataSource.InvalidResponseCodeException
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.PlayerMessage
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import com.crystal.exoplayercodelab.databinding.ActivityPlayerBinding


private const val TAG = "PlayerActivity"

@UnstableApi
class PlayerActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    private val playbackStateListener: Player.Listener = playbackStateListener()


    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlayerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }


    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    @androidx.media3.common.util.UnstableApi
    private fun  initializePlayer() {

        val trackSelector = DefaultTrackSelector(this).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }


        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                viewBinding.videoView.player = exoPlayer

//                val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3))
                val mediaItem = MediaItem.Builder()
                    .setUri(getString(R.string.media_url_dash))
                    .setMimeType(MimeTypes.APPLICATION_MPD)
                    .build()

                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.addListener(playbackStateListener)
                createPlayerMessage(exoPlayer)
                exoPlayer.prepare()
            }
    }



    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, viewBinding.videoView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }


    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.removeListener(playbackStateListener)
            exoPlayer.release()
        }
        player = null
    }

    private fun createPlayerMessage(exoPlayer: ExoPlayer) {
        exoPlayer.createMessage( PlayerMessage.Target { _, message ->
            Log.d(TAG, "playerMessageListener $message?")
            exoPlayer.pause()
            exoPlayer.seekTo(5_000L)
            exoPlayer.play()

        }).setLooper(Looper.getMainLooper())
            .setPosition(/* mediaItemIndex= */ currentItem, /* positionMs= */ 10_000)
            .setPayload("customPayloadData")
            .setDeleteAfterDelivery(true)
            .send()
    }

    private fun playbackStateListener() = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {

            val stateString: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
                else -> "UNKNOWN_STATE             -"
            }
            super.onPlaybackStateChanged(playbackState)
            Log.d(TAG, "changed state to $stateString")

        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            Log.d(TAG, "isPlayingChanged : $isPlaying")
            if (isPlaying) {
                // Active playback.
            } else {
                // Not playing because playback is paused, ended, suppressed, or the player
                // is buffering, stopped or failed. Check player.getPlayWhenReady,
                // player.getPlaybackState, player.getPlaybackSuppressionReason and
                // player.getPlaybackError for details.
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)
            Log.d(TAG, "onPlayerError : $error")
            val cause = error.cause
            if (cause is HttpDataSourceException) {
                // An HTTP error occurred.
                // It's possible to find out more about the error both by casting and by
                // querying the cause.
                if (cause is InvalidResponseCodeException) {
                    // Cast to InvalidResponseCodeException and retrieve the response code,
                    // message and headers.
                } else {
                    // Try calling httpError.getCause() to retrieve the underlying cause,
                    // although note that it may be null.
                }
            }
        }
    }
}