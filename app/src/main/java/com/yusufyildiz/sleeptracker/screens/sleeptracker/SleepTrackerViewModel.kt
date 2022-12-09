package com.yusufyildiz.sleeptracker.screens.sleeptracker

import android.app.Application
import android.text.Spanned
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.yusufyildiz.sleeptracker.database.DatabaseHelper
import com.yusufyildiz.sleeptracker.database.SleepDatabaseDao
import com.yusufyildiz.sleeptracker.database.SleepNight
import com.yusufyildiz.sleeptracker.formatNights
import kotlinx.coroutines.*

class SleepTrackerViewModel(
    private val dbHelper: DatabaseHelper,
    application: Application
) : AndroidViewModel(application) {
    //When a viewmodel is destroyed, onCleared() is called.

    //The scope determines what thread the coroutine will run on and it also needs to know about the job

    //This means coroutines launched in the UI scope will run on the main thread

    var tonight = MutableLiveData<SleepNight?>()
    var nightMutableLiveData = MutableLiveData<List<SleepNight>>()

    init {
        getAllNights()
    }

    fun insertTonight()
    {
        viewModelScope.launch {
            val tonight = SleepNight()
            dbHelper.insert(tonight)
        }
    }

    fun getAllNights()
    {
        viewModelScope.launch {
            val nights = dbHelper.getAllNights()
            if(nights.isNotEmpty())
            {
                nightMutableLiveData.value = nights
            }
        }
    }

    fun onStartTracking() {
        viewModelScope.launch {
            // Create a new night, which captures the current time,
            // and insert it into the database.
            insertTonight().also {
                tonight.value = dbHelper.getTonight()
            }
        }
    }

    fun onStopTracking() {

        viewModelScope.launch {
            // In Kotlin, the return@label syntax is used for specifying which function among
            // several nested ones this statement returns from.
            // In this case, we are specifying to return from launch(),
            // not the lambda.
            val oldNight = tonight.value ?: return@launch

            // Update the night in the database to add the end time.
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
        }
    }

    fun update(night: SleepNight)
    {
        viewModelScope.launch {
            dbHelper.update(night)
        }
    }

    fun onClear() {
        viewModelScope.launch {
            // Clear the database table.
            dbHelper.clear()
            // And clear tonight since it's no longer in the database
            tonight.value = null
        }
    }
}