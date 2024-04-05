package com.fadlurahmanf.starterappmvvm.music_player.domain.receiver

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.fadlurahmanf.starterappmvvm.core.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.music_player.domain.service.MusicPlayerService

class MusicPlayerReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_PAUSE_AUDIO = MusicPlayerService.ACTION_PAUSE_AUDIO
        const val ACTION_RESUME_AUDIO = MusicPlayerService.ACTION_RESUME_AUDIO
        const val ACTION_REWIND_AUDIO = MusicPlayerService.ACTION_REWIND_AUDIO
        const val ACTION_FORWARD_AUDIO = MusicPlayerService.ACTION_FORWARD_AUDIO

        const val ACTION_SEEK_TO_POSITION =
            "com.fadlurahmanf.starterappmvvm.ACTION_SEEK_TO_POSITION"

        const val PARAM_SEEK_TO_POSITION = MusicPlayerService.PARAM_SEEK_TO_POSITION

        fun getPausePendingIntent(context: Context): PendingIntent {
            val intent = Intent(context, MusicPlayerReceiver::class.java)
            intent.apply {
                action = ACTION_PAUSE_AUDIO
            }
            return PendingIntent.getBroadcast(
                context,
                0,
                intent,
                getFlagPendingIntent()
            )
        }

        fun getResumePendingIntent(context: Context): PendingIntent {
            val intent = Intent(context, MusicPlayerReceiver::class.java)
            intent.apply {
                action = ACTION_RESUME_AUDIO
            }
            return PendingIntent.getBroadcast(
                context,
                1,
                intent,
                getFlagPendingIntent()
            )
        }

        fun getRewindPendingIntent(context: Context): PendingIntent {
            val intent = Intent(context, MusicPlayerReceiver::class.java)
            intent.apply {
                action = ACTION_REWIND_AUDIO
            }
            return PendingIntent.getBroadcast(
                context,
                2,
                intent,
                getFlagPendingIntent()
            )
        }

        fun getForwardPendingIntent(context: Context): PendingIntent {
            val intent = Intent(context, MusicPlayerReceiver::class.java)
            intent.apply {
                action = ACTION_FORWARD_AUDIO
            }
            return PendingIntent.getBroadcast(
                context,
                3,
                intent,
                getFlagPendingIntent()
            )
        }

        fun sendBroadcastSeekToPosition(context: Context, position: Long) {
            val intent = Intent(context, MusicPlayerService::class.java)
            intent.apply {
                action = ACTION_SEEK_TO_POSITION
                putExtra(PARAM_SEEK_TO_POSITION, position)
            }
            context.sendBroadcast(intent)
        }

        private fun getFlagPendingIntent(): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        when (intent?.action) {
            ACTION_SEEK_TO_POSITION -> {
                val seekToPosition = intent.getLongExtra(PARAM_SEEK_TO_POSITION, -1L)
                if (seekToPosition != -1L) {
                    MusicPlayerService.seekToPosition(context, seekToPosition)
                }
            }

            ACTION_PAUSE_AUDIO -> {
                MusicPlayerService.pause(context)
            }

            ACTION_RESUME_AUDIO -> {
                MusicPlayerService.resume(context)
            }
        }
    }
}