package com.example.time_tracker.data.local.group

import com.example.time_tracker.base.BaseRepository
import com.example.time_tracker.data.local.relations.GroupWithTasks
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface GroupRepository: BaseRepository<Group> {
    fun getAllGroupsWithTasks(): Flow<List<GroupWithTasks>>
}