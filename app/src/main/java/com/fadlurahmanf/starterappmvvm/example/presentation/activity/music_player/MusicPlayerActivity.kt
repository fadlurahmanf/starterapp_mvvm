package com.fadlurahmanf.starterappmvvm.example.presentation.activity.music_player

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.media3.common.util.UnstableApi
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.databinding.ActivityMusicPlayerBinding
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.music_player.data.state.MusicPlayerState
import com.fadlurahmanf.starterappmvvm.music_player.domain.service.MusicPlayerService


class MusicPlayerActivity :
    BaseExampleActivity<ActivityMusicPlayerBinding>(ActivityMusicPlayerBinding::inflate) {
    override fun onBaseExampleInjectActivity() {}
    private var musicPlayerState = MusicPlayerState.IDLE

    @UnstableApi
    override fun onBaseExampleCreate(savedInstanceState: Bundle?) {
        MusicPlayerService.play(
            this,
            "https://equran.nos.wjv-1.neo.id/audio-full/Abdullah-Al-Juhany/001.mp3"
        )

        binding.ivActionPlayOrPause.setOnClickListener {
            when (musicPlayerState) {
                MusicPlayerState.PAUSED -> {
                    MusicPlayerService.resume(this)
                }

                MusicPlayerState.PLAYING -> {
                    MusicPlayerService.pause(this)
                }

                else -> {

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilterInfo =
            IntentFilter(MusicPlayerService.ACTION_SEND_INFO)
        registerReceiver(broadcastReceiver, intentFilterInfo)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (context == null) return
            when (intent?.action) {
                MusicPlayerService.ACTION_SEND_INFO -> {
                    val position = intent.getLongExtra(MusicPlayerService.PARAM_POSITION, -1L)
                    val duration = intent.getLongExtra(MusicPlayerService.PARAM_DURATION, -1L)
                    if (position != -1L && duration != -1L) {
                        binding.seekBar.max = duration.toInt()
                        binding.seekBar.progress = position.toInt()
                    }

                    try {
                        val state = intent.getStringExtra(MusicPlayerService.PARAM_STATE)
                        val enumState = MusicPlayerState.valueOf(state ?: "")
                        musicPlayerState = enumState
                        when (enumState) {
                            MusicPlayerState.PLAYING -> {
                                binding.ivActionPlayOrPause.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.round_pause_24
                                    )
                                )
                            }

                            else -> {
                                binding.ivActionPlayOrPause.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.round_play_arrow_24
                                    )
                                )
                            }
                        }
                    } catch (e: Exception) {
                        binding.ivActionPlayOrPause.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.round_play_arrow_24
                            )
                        )
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}