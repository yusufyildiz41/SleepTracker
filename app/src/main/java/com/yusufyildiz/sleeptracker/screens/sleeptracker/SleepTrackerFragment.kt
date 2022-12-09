package com.yusufyildiz.sleeptracker.screens.sleeptracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.yusufyildiz.sleeptracker.R
import com.yusufyildiz.sleeptracker.database.DatabaseBuilder
import com.yusufyildiz.sleeptracker.database.DatabaseHelperImp
import com.yusufyildiz.sleeptracker.databinding.FragmentSleepTrackerBinding


class SleepTrackerFragment : Fragment() {

    private lateinit var binding: FragmentSleepTrackerBinding
    private val viewModel : SleepTrackerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sleep_tracker,container,false)

        // Inflate the layout for this fragment
        val application = requireNotNull(this.activity).application
        val dbHelper = DatabaseHelperImp(DatabaseBuilder.getInstance(application.applicationContext))
        val viewModelFactory = SleepTrackerViewModelFactory(dbHelper,application)
        val sleepTrackerViewModel = ViewModelProvider(this,viewModelFactory).get(SleepTrackerViewModel::class.java)
        binding.sleepTrackerViewModel = sleepTrackerViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}