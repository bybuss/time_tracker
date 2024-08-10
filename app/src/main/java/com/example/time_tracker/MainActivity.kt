package com.example.time_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.time_tracker.ui.navigation.AppNavigation
import com.example.time_tracker.ui.screens.auth.TestButtonsScreen
import com.example.time_tracker.ui.theme.MobileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //AppNavigation()
                    //SignUpScreen()
//                    AddRoleTestScreen()
                    TestButtonsScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    AppNavigation()
}