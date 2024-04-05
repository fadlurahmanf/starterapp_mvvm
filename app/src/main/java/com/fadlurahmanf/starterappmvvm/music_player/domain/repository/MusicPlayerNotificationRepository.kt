package com.fadlurahmanf.starterappmvvm.music_player.domain.repository

import android.app.Notification
import android.content.Context

interface MusicPlayerNotificationRepository {
    fun getMultipleAudioNotification(
        context: Context,
        isPlaying: Boolean,
        title: String,
        artist: String,
        duration: Long,
        position: Long,
    ): Notification

    fun getSingleAudioNotification(
        context: Context,
        isPlaying: Boolean,
        title: String,
        artist: String,
        duration: Long,
        position: Long,
    ): Notification

    fun updateSingleAudioNotification(
        context: Context,
        id: Int,
        isPlaying: Boolean,
        title: String,
        artist: String,
        duration: Long,
        position: Long
    )
}