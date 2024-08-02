package com.example.time_tracker.domain.model

/**
 * @author bybuss
 */
data class Permissions (
    val insert: Boolean,
    val update: Boolean,
    val read: Boolean
)
