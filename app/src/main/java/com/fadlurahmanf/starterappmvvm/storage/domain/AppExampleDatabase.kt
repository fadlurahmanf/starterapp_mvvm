package com.fadlurahmanf.starterappmvvm.storage.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fadlurahmanf.starterappmvvm.storage.data.dao.AppExampleDao
import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppExampleEntity
import com.fadlurahmanf.starterappmvvm.storage.others.AppDbConstant

@Database(
    entities = [
        AppExampleEntity::class
    ], version = AppExampleDatabase.VERSION,
    exportSchema = true
)
abstract class AppExampleDatabase : RoomDatabase() {
    abstract fun appExampleDao(): AppExampleDao

    companion object {
        const val VERSION = 1

        @Volatile
        private var INSTANCE: AppExampleDatabase? = null
        fun getDatabase(context: Context): AppExampleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppExampleDatabase::class.java,
                    AppDbConstant.APP_EXAMPLE_DB_NAME
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