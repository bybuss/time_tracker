package com.example.time_tracker.ui.screens.auth

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author bybuss
 */
@AndroidEntryPoint
class PasswordResetActivity: AppCompatActivity() {
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.data
        val token = uri?.lastPathSegment
        Log.d("PasswordResetActivity", token.toString())

        token?.let {
            signUpViewModel.setToken(it)
        }

        finish()
    }
}