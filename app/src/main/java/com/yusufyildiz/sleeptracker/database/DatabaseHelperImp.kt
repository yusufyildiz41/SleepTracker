package com.yusufyildiz.sleeptracker.database

import androidx.lifecycle.LiveData

class DatabaseHelperImp(private val sleepDatabase: SleepDatabase) : DatabaseHelper {

    override suspend fun insert(night: SleepNight) = sleepDatabase.sleepDao().insert(night)

    override suspend fun update(night: SleepNight) = sleepDatabase.sleepDao().update(night)

    override suspend fun get(key: Long): SleepNight = sleepDatabase.sleepDao().get(key)

    override suspend fun clear() = sleepDatabase.sleepDao().clear()

    override suspend fun getAllNights(): List<SleepNight> = sleepDatabase.sleepDao().getAllNights()

    override suspend fun getTonight(): SleepNight? = sleepDatabase.sleepDao().getTonight()
}