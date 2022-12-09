package com.yusufyildiz.sleeptracker.database

import androidx.lifecycle.LiveData
import androidx.room.Query
import androidx.room.Update

interface DatabaseHelper {

    suspend fun insert(night: SleepNight)

    suspend fun update(night: SleepNight)

    suspend fun get(key: Long): SleepNight

    suspend fun clear()

    suspend fun getAllNights(): List<SleepNight>

    suspend fun getTonight(): SleepNight?

}