package com.example.time_tracker.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.time_tracker.TimeTrackerApplication
import com.example.time_tracker.ui.screens.auth.SignUpViewModel
import com.example.time_tracker.ui.screens.createTask.FormViewModel

/**
 * @author bybuss
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SignUpViewModel(
                timeTrackerApplication().container.timeTrackerRepository
            )
        }

        initializer {
            FormViewModel()
        }
    }
}

fun CreationExtras.timeTrackerApplication(): TimeTrackerApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TimeTrackerApplication)
