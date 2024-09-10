package com.example.time_tracker.di

import com.example.time_tracker.data.local.room.group.GroupRepository
import com.example.time_tracker.data.local.room.organization.OrganizationRepository
import com.example.time_tracker.data.local.room.project.ProjectRepository
import com.example.time_tracker.data.local.room.role.RoleRepository
import com.example.time_tracker.data.local.room.task.TaskRepository
import com.example.time_tracker.data.local.room.user.UserRepository
import com.example.time_tracker.data.local.room.userOrg.UserOrgRepository
import com.example.time_tracker.data.local.room.userTask.UserTaskRepository
import com.example.time_tracker.domain.network.GraphQLClient
import com.example.time_tracker.domain.useCase.graphQL.AddOrganizationUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddProjectUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddRoleUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddTaskUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddUserUseCase
import com.example.time_tracker.domain.useCase.graphQL.AuthUserUseCase
import com.example.time_tracker.domain.useCase.graphQL.ChangePasswordUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetAllFullTasksByAssignerIdUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetAllSimpleTasksByAssignerIdUseCase
import com.example.time_tracker.domain.useCase.room.GetAllTasksUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetFullTaskByIdUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetSimpleTaskByIdUseCase
import com.example.time_tracker.domain.useCase.graphQL.RefreshTokenUseCase
import com.example.time_tracker.domain.useCase.graphQL.RequestChangePasswordUseCase
import com.example.time_tracker.domain.useCase.room.GetAllGroupsWithTasksUseCase
import com.example.time_tracker.domain.useCase.room.GetAllOrganizationsUseCase
import com.example.time_tracker.domain.useCase.room.GetAllOrganizationsWithUsersUseCase
import com.example.time_tracker.domain.useCase.room.GetAllProjectsUseCase
import com.example.time_tracker.domain.useCase.room.GetAllRolesUseCase
import com.example.time_tracker.domain.useCase.room.GetAllTasksWithUsersUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUserOrgUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUserTaskUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUsersUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUsersWithOrganizationsUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUsersWithTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author bybuss
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAddRoleUseCase(
        graphQLClient: GraphQLClient
    ): AddRoleUseCase
        = AddRoleUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideAddOrganizationUseCase(
        graphQLClient: GraphQLClient
    ): AddOrganizationUseCase
        = AddOrganizationUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideAddUserUseCase(
        graphQLClient: GraphQLClient
    ): AddUserUseCase
            = AddUserUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideAuthUserUseCase(
        graphQLClient: GraphQLClient
    ): AuthUserUseCase
            = AuthUserUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideRefreshTokenUseCase(
        graphQLClient: GraphQLClient
    ): RefreshTokenUseCase
            = RefreshTokenUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideAddProjectUseCase(
        graphQLClient: GraphQLClient
    ): AddProjectUseCase
            = AddProjectUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideAddTaskUseCase(
        graphQLClient: GraphQLClient
    ): AddTaskUseCase
            = AddTaskUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideGetAllFullTasksByAssignerIdUseCase(
        graphQLClient: GraphQLClient
    ): GetAllFullTasksByAssignerIdUseCase
            = GetAllFullTasksByAssignerIdUseCase(graphQLClient)
    @Provides
    @Singleton
    fun provideGetFullTaskByIdUseCase(
        graphQLClient: GraphQLClient
    ): GetFullTaskByIdUseCase
            = GetFullTaskByIdUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideGetAllSimpleTasksByAssignerIdUseCase(
        graphQLClient: GraphQLClient
    ): GetAllSimpleTasksByAssignerIdUseCase
            = GetAllSimpleTasksByAssignerIdUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideGetSimpleTaskByIdUseCase(
        graphQLClient: GraphQLClient
    ): GetSimpleTaskByIdUseCase
            = GetSimpleTaskByIdUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideRequestChangePasswordUseCase(
        graphQLClient: GraphQLClient
    ): RequestChangePasswordUseCase
            = RequestChangePasswordUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideChangePasswordUseCase(
        graphQLClient: GraphQLClient
    ): ChangePasswordUseCase
            = ChangePasswordUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideGetAllTasksFromRoomUseCase(
        taskRepository: TaskRepository
    ): GetAllTasksUseCase
            = GetAllTasksUseCase(taskRepository)

    @Provides
    @Singleton
    fun provideGetAllRolesFromRoomUseCase(
        roleRepository: RoleRepository
    ): GetAllRolesUseCase
            = GetAllRolesUseCase(roleRepository)

    @Provides
    @Singleton
    fun provideGetAllOrganizationsFromRoomUseCase(
        organizationRepository: OrganizationRepository
    ): GetAllOrganizationsUseCase
        = GetAllOrganizationsUseCase(organizationRepository)

    @Provides
    @Singleton
    fun provideGetAllProjectsFromRoomUseCase(
        projectRepository: ProjectRepository
    ): GetAllProjectsUseCase
        = GetAllProjectsUseCase(projectRepository)

    @Provides
    @Singleton
    fun provideGetAllUsersFromRoomUseCase(
        userRepository: UserRepository
    ): GetAllUsersUseCase
        = GetAllUsersUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetAllTasksWithUsersFromRoomUseCase(
        taskRepository: TaskRepository
    ): GetAllTasksWithUsersUseCase
        = GetAllTasksWithUsersUseCase(taskRepository)

    @Provides
    @Singleton
    fun provideGetAllUsersWithTasksFromRoomUseCase(
        userRepository: UserRepository
    ): GetAllUsersWithTasksUseCase
        = GetAllUsersWithTasksUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetAllUsersWithOrganizationsFromRoomUseCase(
        userRepository: UserRepository
    ): GetAllUsersWithOrganizationsUseCase
        = GetAllUsersWithOrganizationsUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetAllOrganizationsWithUsersFromRoomUseCase(
        organizationRepository: OrganizationRepository
    ): GetAllOrganizationsWithUsersUseCase
        = GetAllOrganizationsWithUsersUseCase(organizationRepository)

    @Provides
    @Singleton
    fun provideGetAllUserOrgFromRoomUseCase(
        userOrgRepository: UserOrgRepository
    ): GetAllUserOrgUseCase
        = GetAllUserOrgUseCase(userOrgRepository)

    @Provides
    @Singleton
    fun provideGetAllUserTaskFromRoomUseCase(
        userTaskRepository: UserTaskRepository
    ): GetAllUserTaskUseCase
        = GetAllUserTaskUseCase(userTaskRepository)

    @Provides
    @Singleton
    fun provideGetAllGroupsWithTasksFromRoomUseCase(
        groupRepository: GroupRepository
    ): GetAllGroupsWithTasksUseCase
        = GetAllGroupsWithTasksUseCase(groupRepository)
}