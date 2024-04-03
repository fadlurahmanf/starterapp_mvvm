package com.fadlurahmanf.starterappmvvm.music_player.others

import android.content.Context
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache

@UnstableApi
object MusicPlayerShared {
    private lateinit var databaseProvider: StandaloneDatabaseProvider
    private lateinit var simpleCache: SimpleCache

    fun getDatabaseProvider(context: Context): StandaloneDatabaseProvider {
        if (!this::databaseProvider.isInitialized) {
            databaseProvider = StandaloneDatabaseProvider(context)
        }
        return databaseProvider
    }

    fun getSimpleCache(context: Context): SimpleCache {
        if (!this::simpleCache.isInitialized) {
            simpleCache = SimpleCache(
                context.cacheDir,
                LeastRecentlyUsedCacheEvictor(Long.MAX_VALUE),
                getDatabaseProvider(context)
            )
        }
        return simpleCache
    }
}