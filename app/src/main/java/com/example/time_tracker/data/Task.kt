package com.example.time_tracker.data

data class TaskItem(
    val id: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val startTime: String,
    val endTime: String,
    val isActive: Boolean,
    val projectId: Int? = null
)

val tasks = mutableListOf<TaskItem>(
    TaskItem(
        id = 1,
        title = "Спортзал",
        startDate = "2024/02/14",
        endDate = "2024/02/14",
        startTime = "07:00",
        endTime = "08:30",
        isActive = true,
        projectId = null
    ),
    TaskItem(
        id = 2,
        title = "Встреча с клиентом",
        startDate = "2024/02/15",
        endDate = "2024/02/15",
        startTime = "10:00",
        endTime = "11:00",
        isActive = false,
        projectId = 102
    ),
    TaskItem(
        id = 3,
        title = "Разработка отчета",
        startDate = "2024/02/14",
        endDate = "2024/02/16",
        startTime = "09:00",
        endTime = "17:00",
        isActive = false,
        projectId = 101
    ),
    TaskItem(
        id = 4,
        title = "Командная встреча",
        startDate = "2024/02/17",
        endDate = "2024/02/17",
        startTime = "13:00",
        endTime = "14:00",
        isActive = false,
        projectId = 103
    ),
    TaskItem(
        id = 5,
        title = "Обзор кода",
        startDate = "2024/02/18",
        endDate = "2024/02/18",
        startTime = "15:00",
        endTime = "16:00",
        isActive = false,
        projectId = 104
    ),
    TaskItem(
        id = 6,
        title = "Планирование спринта",
        startDate = "2024/02/19",
        endDate = "2024/02/19",
        startTime = "11:00",
        endTime = "12:30",
        isActive = false,
        projectId = 105
    ),
    TaskItem(
        id = 7,
        title = "Обновление документации",
        startDate = "2024/02/20",
        endDate = "2024/02/20",
        startTime = "14:00",
        endTime = "15:30",
        isActive = false,
        projectId = 106
    ),
    TaskItem(
        id = 8,
        title = "Прототипирование нового дизайна",
        startDate = "2024/02/21",
        endDate = "2024/02/21",
        startTime = "10:00",
        endTime = "12:00",
        isActive = false,
        projectId = 101
    ),
    TaskItem(
        id = 9,
        title = "Тестирование пользовательского интерфейса",
        startDate = "2024/02/22",
        endDate = "2024/02/22",
        startTime = "09:00",
        endTime = "10:30",
        isActive = false,
        projectId = 101
    ),
    TaskItem(
        id = 10,
        title = "Анализ пользовательских отзывов",
        startDate = "2024/02/23",
        endDate = "2024/02/23",
        startTime = "16:00",
        endTime = "17:30",
        isActive = false,
        projectId = 109
    )
)