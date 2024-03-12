package com.fadlurahmanf.starterappmvvm.storage.others

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppEntity

@Database(
    entities = [
        AppEntity::class
    ], version = BaseDatabase.VERSION,
    exportSchema = true
)
abstract class BaseDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1

        @Volatile
        private var INSTANCE: BaseDatabase? = null
        fun getDatabase(context: Context): BaseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDatabase::class.java,
                    "appDatabase"
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