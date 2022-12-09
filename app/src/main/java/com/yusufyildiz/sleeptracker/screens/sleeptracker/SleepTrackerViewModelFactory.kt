package com.yusufyildiz.sleeptracker.screens.sleeptracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yusufyildiz.sleeptracker.database.DatabaseHelper
import com.yusufyildiz.sleeptracker.database.SleepDatabaseDao

class SleepTrackerViewModelFactory(
    private val dataSource: DatabaseHelper,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java)) {
            return SleepTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}