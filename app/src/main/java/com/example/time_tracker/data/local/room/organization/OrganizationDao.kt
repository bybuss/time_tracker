package com.example.time_tracker.data.local.room.organization

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.time_tracker.data.local.room.base.BaseDao
import com.example.time_tracker.data.local.room.relations.OrganizationWithProjects
import com.example.time_tracker.data.local.room.relations.userOrg.OrganizationWithUsers
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface OrganizationDao: BaseDao<Organization> {
    @Query("SELECT * FROM organizations")
    fun getAllOrganizations(): Flow<List<Organization>>

    @Transaction
    @Query("SELECT * FROM organizations")
    fun getAllOrganizationsWithProjects(): Flow<List<OrganizationWithProjects>>

    @Transaction
    @Query("SELECT * FROM organizations")
    fun getAllOrganizationsWithUsers(): Flow<List<OrganizationWithUsers>>
}