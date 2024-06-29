package com.example.time_tracker.ui.viewmodels.form

import androidx.lifecycle.ViewModel
import com.example.time_tracker.data.TaskItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FormViewModel: ViewModel(){

    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    private val _tasks = MutableStateFlow(mutableListOf<TaskItem>())
    val tasks: StateFlow<List<TaskItem>> = _tasks.asStateFlow()

    fun updateTitle(title: String){
        _uiState.value = _uiState.value.copy(title = title)
    }
    fun updateStartDate(startDate: String){
        _uiState.value = _uiState.value.copy(startDate = startDate)
    }
    fun updateEndDate(endDate: String){
        _uiState.value = _uiState.value.copy(endDate = endDate)
    }
    fun updateStartTime(startTime: String){
        _uiState.value = _uiState.value.copy(startTime = startTime)
    }
    fun updateEndTime(endTime: String){
        _uiState.value = _uiState.value.copy(endTime = endTime)
    }
    fun updateAsanaUrl(asanaUrl: String){
        _uiState.value = _uiState.value.copy(asanaUrl = asanaUrl)
    }

    fun addTask(){
        val currentState = _uiState.value
        if (
            currentState.title.isNotEmpty() && currentState.startDate.isNotEmpty() &&
            currentState.endDate.isNotEmpty() && currentState.startTime.isNotEmpty() &&
            currentState.endTime.isNotEmpty()
            ){
            val newTask = TaskItem(
                id = generateNewId(),
                title = currentState.title,
                startDate = currentState.startDate,
                endDate = currentState.endDate,
                startTime = currentState.startTime,
                endTime = currentState.endTime,
                isActive = false,
                projectId = null
            )
            _tasks.value = ArrayList(_tasks.value).apply {
                add(newTask)
            }
            clearForm()
        } else{
            // TODO: 11.04.2022 Добавить ошибку, если в полях заполнены пустые значения
        }
    }

    // Очистка формы
    fun clearForm(){
        _uiState.value = FormUiState()
    }

    private fun generateNewId(): Int {
        return if (_tasks.value.isNotEmpty()) _tasks.value.last().id + 1  else 1
    }
}