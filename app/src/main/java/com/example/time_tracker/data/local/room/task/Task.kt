package com.example.time_tracker.data.local.room.task

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author bybuss
 */

/**
 * [assignerId], [projectId] и [groupId] могут быть null, потому что они могут не принадлежать ни к
 * какой группе или проекту, а в случае с создателем задачи, при его удалении
 * (аккаунта создателя задачи) задача может остатьсяв проекте или группе.
 * */
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val name: String,
    val description: String,
    val isDone: Boolean = false,
    val addedAt: String,
    val endDate: String? = null,
    val doneAt: String? = null,
    val assignerId: String? = null, // ForeignKey
    val duration: Int,
    val color: String,
    val difficulty: String,
    val projectId: Int? = null, // ForeignKey ✅
    val groupId: Int? = null // ForeignKey ✅
)