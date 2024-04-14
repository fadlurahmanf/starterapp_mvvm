package com.fadlurahmanf.starterappmvvm.music_player.domain.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.annotation.OptIn
import androidx.core.content.ContextCompat
import androidx.media3.common.util.UnstableApi
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.music_player.data.state.MusicPlayerState
import com.fadlurahmanf.starterappmvvm.music_player.domain.repository.MusicPlayerNotificationRepository
import com.fadlurahmanf.starterappmvvm.music_player.domain.repository.MusicPlayerNotificationRepositoryImpl
import com.fadlurahmanf.starterappmvvm.music_player.presentation.MusicPlayer

class MusicPlayerService : Service(), MusicPlayer.Callback {
    private val musicPlayerNotificationRepository: MusicPlayerNotificationRepository =
        MusicPlayerNotificationRepositoryImpl()
    private lateinit var musicPlayer: MusicPlayer

    companion object {
        const val ACTION_PLAY_AUDIO = "com.fadlurahmanf.starterappmvvm.ACTION_PLAY_AUDIO"
        const val ACTION_PAUSE_AUDIO = "com.fadlurahmanf.starterappmvvm.ACTION_PAUSE_AUDIO"
        const val ACTION_RESUME_AUDIO = "com.fadlurahmanf.starterappmvvm.ACTION_RESUME_AUDIO"
        const val ACTION_REWIND_AUDIO = "com.fadlurahmanf.starterappmvvm.ACTION_REWIND_AUDIO"
        const val ACTION_FORWARD_AUDIO = "com.fadlurahmanf.starterappmvvm.ACTION_FORWARD_AUDIO"
        const val ACTION_SEEK_TO_POSITION =
            "com.fadlurahmanf.starterappmvvm.ACTION_SEEK_TO_POSITION"
        const val ACTION_SEND_INFO =
            "com.fadlurahmanf.starterappmvvm.ACTION_SEND_INFO"

        const val PARAM_AUDIO_URL = "PARAM_AUDIO_URL"
        const val PARAM_SEEK_TO_POSITION = "PARAM_SEEK_TO_POSITION"
        const val PARAM_POSITION = "PARAM_POSITION"
        const val PARAM_DURATION = "PARAM_DURATION"
        const val PARAM_STATE = "PARAM_STATE"

        fun play(context: Context, audioUrl: String) {
            val intent = Intent(context, MusicPlayerService::class.java)
            intent.apply {
                action = ACTION_PLAY_AUDIO
                putExtra(PARAM_AUDIO_URL, audioUrl)
            }
            ContextCompat.startForegroundService(context, intent)
        }

        fun pause(context: Context) {
            val intent = Intent(context, MusicPlayerService::class.java)
            intent.apply {
                action = ACTION_PAUSE_AUDIO
            }
            ContextCompat.startForegroundService(context, intent)
        }

        fun resume(context: Context) {
            val intent = Intent(context, MusicPlayerService::class.java)
            intent.apply {
                action = ACTION_RESUME_AUDIO
            }
            ContextCompat.startForegroundService(context, intent)
        }

        fun seekToPosition(context: Context, position: Long) {
            val intent = Intent(context, MusicPlayerService::class.java)
            intent.apply {
                action = ACTION_SEEK_TO_POSITION
                putExtra(PARAM_SEEK_TO_POSITION, position)
            }
            ContextCompat.startForegroundService(context, intent)
        }

        fun sendBroadcastSendInfo(
            context: Context,
            position: Long,
            duration: Long,
            state: MusicPlayerState
        ) {
            val intent = Intent().apply {
                action = ACTION_SEND_INFO
                putExtra(PARAM_DURATION, duration)
                putExtra(PARAM_POSITION, position)
                putExtra(PARAM_STATE, state.name)
            }
            context.sendBroadcast(intent)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!this::musicPlayer.isInitialized) {
            musicPlayer = MusicPlayer()
            musicPlayer.setCallback(this)
        }
        when (intent?.action) {
            ACTION_PLAY_AUDIO -> {
                val audioUrl = intent.getStringExtra(PARAM_AUDIO_URL)
                if (audioUrl != null) {
                    musicPlayer.play(applicationContext, audioUrl)
                }
            }

            ACTION_PAUSE_AUDIO -> {
                musicPlayer.pause()
                musicPlayerNotificationRepository.updateSingleAudioNotification(
                    applicationContext,
                    1,
                    false,
                    "Title",
                    "Artist",
                    musicPlayer.duration,
                    musicPlayer.position,
                )
            }

            ACTION_RESUME_AUDIO -> {
                musicPlayer.resume()
                musicPlayerNotificationRepository.updateSingleAudioNotification(
                    applicationContext,
                    1,
                    true,
                    "Title",
                    "Artist",
                    musicPlayer.duration,
                    musicPlayer.position,
                )
            }



            ACTION_SEEK_TO_POSITION -> {
                val seekToPosition = intent.getLongExtra(PARAM_SEEK_TO_POSITION, -1L)
                if (seekToPosition != -1L) {
                    musicPlayer.seekToPosition(seekToPosition)
                }
            }
        }
        return START_STICKY
    }

    @OptIn(UnstableApi::class)
    override fun onPositionChanged(position: Long) {
        super.onPositionChanged(position)
        sendBroadcastSendInfo(
            applicationContext,
            musicPlayer.position,
            musicPlayer.duration,
            musicPlayer.musicPlayerState
        )
    }


    override fun onStateChanged(state: MusicPlayerState) {
        super.onStateChanged(state)
        Log.d(AppConstant.LOGGER_TAG, "MusicPlayerService onStateChanged: $state")
        sendBroadcastSendInfo(
            applicationContext,
            musicPlayer.position,
            musicPlayer.duration,
            musicPlayer.musicPlayerState
        )
        when (state) {
            MusicPlayerState.RESUME -> {

            }
            MusicPlayerState.READY -> {
                startForeground(
                    1,
                    musicPlayerNotificationRepository.getSingleAudioNotification(
                        applicationContext,
                        true,
                        "Title",
                        "Artist",
                        musicPlayer.duration,
                        musicPlayer.position,
                    )
                )
            }

            else -> {

            }
        }
    }

    @OptIn(UnstableApi::class)
    override fun onDestroy() {
        musicPlayer.destroy()
        super.onDestroy()
    }
}