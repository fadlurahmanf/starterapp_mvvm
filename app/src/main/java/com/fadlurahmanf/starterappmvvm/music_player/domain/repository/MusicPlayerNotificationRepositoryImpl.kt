package com.fadlurahmanf.starterappmvvm.music_player.domain.repository

import android.app.Notification
import android.content.Context
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.core.notification.others.BaseNotificationService
import com.fadlurahmanf.starterappmvvm.music_player.domain.receiver.MusicPlayerReceiver

class MusicPlayerNotificationRepositoryImpl : BaseNotificationService(),
    MusicPlayerNotificationRepository {

    private fun getMediaNotification(
        context: Context,
        isPlaying: Boolean,
        title: String,
        artist: String,
        duration: Long,
        position: Long,
    ): NotificationCompat.Builder {
        createMediaNotificationChannel(context)
        val mediaSession = MediaSessionCompat(context, "MusicPlayerService")
        mediaSession.setPlaybackState(
            PlaybackStateCompat.Builder()
                .setState(
                    if (isPlaying) PlaybackStateCompat.STATE_PLAYING else PlaybackStateCompat.STATE_PAUSED,
                    position,
                    1f
                )
                .setActions(PlaybackStateCompat.ACTION_SEEK_TO)
                .build()
        )
        mediaSession.setMetadata(
            MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist)
                .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, duration)
                .build()
        )
        mediaSession.setCallback(object : MediaSessionCompat.Callback() {
            override fun onSeekTo(pos: Long) {
                super.onSeekTo(pos)
                Log.d(AppConstant.LOGGER_TAG, "MediaSessionCompat Position: $pos")
                MusicPlayerReceiver.sendBroadcastSeekToPosition(context, position)
//                val infoIntent = Intent()
//                infoIntent.apply {
//                    action = ACTION_MEDIA_PLAYER_NOTIFICATION_HELPER
//                    putExtra(EXTRA_CURRENT_POSITION, pos)
//                    putExtra(EXTRA_DURATION_AUDIO, duration.toLong())
//                }
//                context.sendBroadcast(infoIntent)
            }
        })
        val mediaStyle = androidx.media.app.NotificationCompat.MediaStyle()
            .setMediaSession(mediaSession.sessionToken)
            .setShowActionsInCompactView(0, 1, 2)
        return NotificationCompat.Builder(context, MEDIA_CHANNEL_ID)
            .setSmallIcon(R.drawable.il_logo_bankmas).setStyle(mediaStyle)
    }

    override fun getMultipleAudioNotification(
        context: Context,
        isPlaying: Boolean,
        title: String,
        artist: String,
        duration: Long,
        position: Long,
    ): Notification {
        return getMediaNotification(context, isPlaying, title, artist, duration, position)
//            .addAction(
//                NotificationCompat.Action(
//                    R.drawable.round_fast_rewind_24,
//                    "Previous",
//                    getPreviousPendingIntent()
//                )
//            )
//            .addAction(
//                NotificationCompat.Action(
//                    R.drawable.ic_pause,
//                    "Pause",
//                    getPausePendingIntent()
//                )
//            )
//            .addAction(
//                NotificationCompat.Action(
//                    R.drawable.round_skip_next_24,
//                    "Next",
//                    getNextPendingIntent()
//                )
//            )
            .setOngoing(true)
            .build()
    }

    override fun getSingleAudioNotification(
        context: Context,
        isPlaying: Boolean,
        title: String,
        artist: String,
        duration: Long,
        position: Long
    ): Notification {
        val notification = getMediaNotification(context, isPlaying, title, artist, duration, position)
            .addAction(
                NotificationCompat.Action(
                    R.drawable.round_fast_rewind_24,
                    "Rewind",
                    MusicPlayerReceiver.getRewindPendingIntent(context)
                )
            )

        if (isPlaying) {
            notification
                .addAction(
                    NotificationCompat.Action(
                        R.drawable.round_pause_24,
                        "Pause",
                        MusicPlayerReceiver.getPausePendingIntent(context)
                    )
                )
        } else {
            notification
                .addAction(
                    NotificationCompat.Action(
                        R.drawable.round_play_arrow_24,
                        "Resume",
                        MusicPlayerReceiver.getResumePendingIntent(context)
                    )
                )
        }

        notification
            .addAction(
                NotificationCompat.Action(
                    R.drawable.round_fast_forward_24,
                    "Forward",
                    MusicPlayerReceiver.getForwardPendingIntent(context)
                )
            )

        notification.setOngoing(isPlaying)

        return notification.build()
    }

    override fun updateSingleAudioNotification(
        context: Context,
        id: Int,
        isPlaying: Boolean,
        title: String,
        artist: String,
        duration: Long,
        position: Long
    ) {
        getNotificationManager(context).notify(
            id,
            getSingleAudioNotification(context, isPlaying, title, artist, duration, position)
        )
    }
}