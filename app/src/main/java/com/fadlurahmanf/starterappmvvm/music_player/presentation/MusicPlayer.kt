package com.fadlurahmanf.starterappmvvm.music_player.presentation

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.Listener
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.fadlurahmanf.starterappmvvm.core.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.music_player.data.state.MusicPlayerState
import com.fadlurahmanf.starterappmvvm.music_player.others.MusicPlayerShared

@UnstableApi
class MusicPlayer : Listener, AnalyticsListener {
    private lateinit var exoPlayer: ExoPlayer
    private var callback: Callback? = null
    private var _duration: Long = 0L
    val duration: Long
        get() = _duration
    private var _position: Long = 0L
    val position: Long
        get() = _position
    private var _musicPlayerState: MusicPlayerState = MusicPlayerState.IDLE
    val musicPlayerState: MusicPlayerState
        get() = _musicPlayerState

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun play(context: Context, uriString: String) {
        exoPlayer = ExoPlayer.Builder(context).build()
        exoPlayer.addListener(this)
        exoPlayer.addAnalyticsListener(this)
        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
        val mediaItem = MediaItem.fromUri(Uri.parse(uriString))
        val cache = MusicPlayerShared.getSimpleCache(context)
        val cacheDataSourceFactory =
            CacheDataSource.Factory()
                .setCache(cache)
                .setUpstreamDataSourceFactory(dataSourceFactory)
                .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
        val mediaSource: MediaSource =
            ProgressiveMediaSource.Factory(cacheDataSourceFactory)
                .createMediaSource(mediaItem)
        exoPlayer.setMediaSource(mediaSource)
        updateOnStateChanged(MusicPlayerState.IDLE)
        exoPlayer.playWhenReady = true
        exoPlayer.prepare()
    }

    fun pause() {
        if (musicPlayerState == MusicPlayerState.PLAYING) {
            exoPlayer.pause()
            updateOnStateChanged(MusicPlayerState.PAUSED)
        }
    }

    fun resume() {
        if (musicPlayerState == MusicPlayerState.PAUSED) {
            exoPlayer.play()
            updateOnStateChanged(MusicPlayerState.RESUME)
            handler.postDelayed({ updateOnStateChanged(MusicPlayerState.PLAYING) }, 500)
        }
    }

    /**
     * position: in milliSecond
     */
    fun seekToPosition(position: Long) {
        exoPlayer.seekTo(position)
    }

    private val handler = Handler(Looper.getMainLooper())
    private val fetchDurationAndPositionRunnable = object : Runnable {
        override fun run() {
            if (exoPlayer.currentPosition > 0 && position != exoPlayer.currentPosition) {
                _position = exoPlayer.currentPosition
                callback?.onPositionChanged(position)
            }

            handler.postDelayed(this, 250)
        }
    }

    override fun onIsLoadingChanged(isLoading: Boolean) {
        super<Listener>.onIsLoadingChanged(isLoading)
        Log.d(AppConstant.LOGGER_TAG, "IS LOADING -> $isLoading")
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super<Listener>.onIsPlayingChanged(isPlaying)
        Log.d(AppConstant.LOGGER_TAG, "IS PLAYING -> $isPlaying")
        if (isPlaying && musicPlayerState != MusicPlayerState.PLAYING) {
            updateOnStateChanged(MusicPlayerState.PLAYING)
        }
    }

    override fun onPlaybackStateChanged(eventTime: AnalyticsListener.EventTime, state: Int) {
        super<AnalyticsListener>.onPlaybackStateChanged(eventTime, state)
        Log.d(AppConstant.LOGGER_TAG, "PLAYBACK STATE -> $state")
        if (state == Player.STATE_IDLE && musicPlayerState != MusicPlayerState.IDLE) {
            updateOnStateChanged(MusicPlayerState.IDLE)
        } else if (state == Player.STATE_BUFFERING && musicPlayerState != MusicPlayerState.LOADING) {
            updateOnStateChanged(MusicPlayerState.LOADING)
        } else if (state == Player.STATE_READY && musicPlayerState != MusicPlayerState.READY) {
            _duration = exoPlayer.duration
            callback?.onDurationFetched(duration)
            handler.post(fetchDurationAndPositionRunnable)
            updateOnStateChanged(MusicPlayerState.READY)
        } else if (state == Player.STATE_ENDED && musicPlayerState != MusicPlayerState.ENDED) {
            updateOnStateChanged(MusicPlayerState.ENDED)
        }
    }

    private fun updateOnStateChanged(state: MusicPlayerState) {
        _musicPlayerState = state
        callback?.onStateChanged(state)
    }

    fun destroy() {
        handler.removeCallbacks(fetchDurationAndPositionRunnable)
        exoPlayer.pause()
        exoPlayer.stop()
        exoPlayer.release()
    }

    interface Callback {
        fun onStateChanged(state: MusicPlayerState) {}
        fun onDurationFetched(duration: Long) {}
        fun onPositionChanged(position: Long) {}
    }
}