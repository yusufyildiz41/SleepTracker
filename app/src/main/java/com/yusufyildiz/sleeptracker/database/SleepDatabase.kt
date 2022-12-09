package com.yusufyildiz.sleeptracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {
    abstract fun sleepDao(): SleepDatabaseDao
}

object DatabaseBuilder {

    private var INSTANCE: SleepDatabase? =null

    fun getInstance(context: Context): SleepDatabase
    {
        if(INSTANCE == null)
        {
            synchronized(SleepDatabase::class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = buildRoomDB(context)
                }
            }
        }
        return INSTANCE!!
    }
    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            SleepDatabase::class.java,
            "sleepdatabase"
        ).build()
}