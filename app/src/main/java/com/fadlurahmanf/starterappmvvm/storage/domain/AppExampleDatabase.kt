package com.fadlurahmanf.starterappmvvm.storage.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppExampleEntity

@Database(
    entities = [
        AppExampleEntity::class
    ], version = AppExampleDatabase.VERSION,
    exportSchema = true
)
abstract class AppExampleDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1

        @Volatile
        private var INSTANCE: AppExampleDatabase? = null
        fun getDatabase(context: Context): AppExampleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppExampleDatabase::class.java,
                    "appExampleDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}