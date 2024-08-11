package com.example.time_tracker.ui.screens.auth

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.time_tracker.ui.AppViewModelProvider

/**
 * @author bybuss
 */
class PasswordResetActivity: AppCompatActivity() {
    private val signUpViewModel: SignUpViewModel by viewModels { AppViewModelProvider.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.data
        val token = uri?.lastPathSegment
        Log.d("token", token.toString())

        token?.let {
            signUpViewModel.setToken(it)
        }

        finish()
    }
}