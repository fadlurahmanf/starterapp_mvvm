package com.fadlurahmanf.starterappmvvm.example.presentation.activity.music_player

import android.os.Bundle
import android.util.Log
import androidx.annotation.OptIn
import androidx.core.content.ContextCompat
import androidx.media3.common.util.UnstableApi
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.core.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.databinding.ActivityMusicPlayerBinding
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.music_player.data.state.MusicPlayerState
import com.fadlurahmanf.starterappmvvm.music_player.presentation.MusicPlayer


class MusicPlayerActivity :
    BaseExampleActivity<ActivityMusicPlayerBinding>(ActivityMusicPlayerBinding::inflate),
    MusicPlayer.Callback {
    override fun onBaseExampleInjectActivity() {
    }

    @UnstableApi
    lateinit var musicPlayer: MusicPlayer

    @UnstableApi
    override fun onBaseExampleCreate(savedInstanceState: Bundle?) {
        musicPlayer = MusicPlayer()
        musicPlayer.setCallback(this)
        musicPlayer.play(
            this,
            "https://equran.nos.wjv-1.neo.id/audio-full/Abdullah-Al-Juhany/001.mp3"
        )

        binding.ivActionPlayOrPause.setOnClickListener {
            when (musicPlayer.musicPlayerState) {
                MusicPlayerState.PLAYING -> {
                    musicPlayer.pause()
                }

                MusicPlayerState.PAUSED -> {
                    musicPlayer.resume()
                }

                else -> {

                }
            }
        }
    }

    @OptIn(UnstableApi::class)
    override fun onStateChanged(state: MusicPlayerState) {
        super.onStateChanged(state)
        Log.d(AppConstant.LOGGER_TAG, "MUSIC PLAYER STATE: $state")
        when (state) {
            MusicPlayerState.PLAYING -> {
                binding.ivActionPlayOrPause.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.round_pause_24
                    )
                )
            }

            else -> {
                binding.ivActionPlayOrPause.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.round_play_arrow_24
                    )
                )
            }
        }
    }

    @OptIn(UnstableApi::class)
    override fun onDurationChanged(duration: Long) {
        super.onDurationChanged(duration)
        Log.d(AppConstant.LOGGER_TAG, "DURATION: $duration")
        binding.seekBar.max = duration.toInt()
    }

    @OptIn(UnstableApi::class)
    override fun onPositionChanged(position: Long) {
        super.onPositionChanged(position)
        Log.d(AppConstant.LOGGER_TAG, "POSITION: $position")
        binding.seekBar.progress = position.toInt()
    }

    @OptIn(UnstableApi::class)
    override fun onDestroy() {
        musicPlayer.destroy()
        super.onDestroy()
    }
}