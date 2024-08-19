package com.example.time_tracker.domain.model

/**
 * @author bybuss
 */
data class ButtonAction(
    val label: String,
    val action: () -> Unit
)