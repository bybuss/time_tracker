package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.group.GroupRepository
import com.example.time_tracker.data.local.room.relations.GroupWithTasks
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllGroupsWithTasksUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {

    fun getAllGroupsWithTasks(): Flow<List<GroupWithTasks>> {
        return groupRepository.getAllGroupsWithTasks()
    }

}