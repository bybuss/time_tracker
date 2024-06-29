package com.example.time_tracker

import androidx.compose.runtime.Composable
import java.util.Calendar
import java.util.Locale

@Composable
fun CurrentDate(): String {
    val calendar = Calendar.getInstance()
    val monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("ru"))
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale("ru"))

    return "$dayOfMonth $monthName $dayOfWeek"
}

