package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.role.Role
import com.example.time_tracker.data.local.room.role.RoleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllRolesUseCase @Inject constructor(private val roleRepository: RoleRepository) {

    fun getAllRoles(): Flow<List<Role>> {
        return roleRepository.getAllRoles()
    }
}