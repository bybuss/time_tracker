package com.example.time_tracker.domain.model

/**
 * @author bybuss
 */
data class FullTask(
    val id: Int,
    val name: String,
    val description: String?,
    val isDone: Boolean,
    val addedAt: String,
    val doneAt: String?,
    val assignerId: String,
    val color: String?,
    val duration: Int,
    val difficulty: String?,
    val projectId: Int,
    val groupId: Int?
)