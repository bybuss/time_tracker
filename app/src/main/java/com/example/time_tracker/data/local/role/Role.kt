package com.example.time_tracker.data.local.role

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author bybuss
 */
@Entity(tableName = "roles")
data class Role(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
//FIXME: ДОБАВИТЬ КОНВЕРТОР ДЛЯ СЛОЖНЫХ ТИПОВ ДАННЫХ, ТК БД НЕ МОЖЕТ ЗАПИСАТЬ permissions
//    val permissions: Map<String, Map<String, Boolean>>
)