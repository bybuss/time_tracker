package com.example.time_tracker.domain.model

import java.time.LocalDateTime

/**
 * @author bybuss
 */
data class SimpleTask(
    val id: Int,
    val name: String,
    val isDone: Boolean,
    val addedAt: String,
    val doneAt: String?
)
