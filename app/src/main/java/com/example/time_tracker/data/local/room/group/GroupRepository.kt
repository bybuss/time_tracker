package com.example.time_tracker.data.local.room.group

import com.example.time_tracker.data.local.room.base.BaseRepository
import com.example.time_tracker.data.local.room.relations.GroupWithTasks
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface GroupRepository: BaseRepository<Group> {
    fun getAllGroupsWithTasks(): Flow<List<GroupWithTasks>>
}