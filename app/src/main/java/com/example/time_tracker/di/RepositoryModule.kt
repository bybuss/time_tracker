package com.example.time_tracker.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.example.time_tracker.data.local.dataStore.TokenDataSource
import com.example.time_tracker.data.local.dataStore.TokenDataSourceImpl
import com.example.time_tracker.data.local.room.group.GroupDao
import com.example.time_tracker.data.local.room.group.GroupRepository
import com.example.time_tracker.data.local.room.group.GroupRepositoryImpl
import com.example.time_tracker.data.local.room.organization.OrganizationDao
import com.example.time_tracker.data.local.room.organization.OrganizationRepository
import com.example.time_tracker.data.local.room.organization.OrganizationRepositoryImpl
import com.example.time_tracker.data.local.room.project.ProjectDao
import com.example.time_tracker.data.local.room.project.ProjectRepository
import com.example.time_tracker.data.local.room.project.ProjectRepositoryImpl
import com.example.time_tracker.data.local.room.role.RoleDao
import com.example.time_tracker.data.local.room.role.RoleRepository
import com.example.time_tracker.data.local.room.role.RoleRepositoryImpl
import com.example.time_tracker.data.local.room.task.TaskDao
import com.example.time_tracker.data.local.room.task.TaskRepository
import com.example.time_tracker.data.local.room.task.TaskRepositoryImpl
import com.example.time_tracker.data.local.room.user.UserDao
import com.example.time_tracker.data.local.room.user.UserRepository
import com.example.time_tracker.data.local.room.user.UserRepositoryImpl
import com.example.time_tracker.data.local.room.userOrg.UserOrgDao
import com.example.time_tracker.data.local.room.userOrg.UserOrgRepository
import com.example.time_tracker.data.local.room.userOrg.UserOrgRepositoryImpl
import com.example.time_tracker.data.local.room.userTask.UserTaskDao
import com.example.time_tracker.data.local.room.userTask.UserTaskRepository
import com.example.time_tracker.data.local.room.userTask.UserTaskRepositoryImpl
import com.example.time_tracker.data.network.GraphQLClientImpl
import com.example.time_tracker.domain.network.GraphQLClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author bybuss
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTokenDataSource(@ApplicationContext context: Context): TokenDataSource
        = TokenDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository = TaskRepositoryImpl(taskDao)

    @Provides
    @Singleton
    fun provideRoleRepository(roleDao: RoleDao): RoleRepository = RoleRepositoryImpl(roleDao)

    @Provides
    @Singleton
    fun provideOrganizationRepository(organizationDao: OrganizationDao): OrganizationRepository
        = OrganizationRepositoryImpl(organizationDao)

    @Provides
    @Singleton
    fun provideProjectRepository(projectDao: ProjectDao): ProjectRepository
        = ProjectRepositoryImpl(projectDao)

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)

    @Provides
    @Singleton
    fun provideUserOrgRepository(userOrgDao: UserOrgDao): UserOrgRepository
        = UserOrgRepositoryImpl(userOrgDao)

    @Provides
    @Singleton
    fun provideUserTaskRepository(userTaskDao: UserTaskDao): UserTaskRepository
        = UserTaskRepositoryImpl(userTaskDao)

    @Provides
    @Singleton
    fun provideGroupRepository(groupDao: GroupDao): GroupRepository = GroupRepositoryImpl(groupDao)

    @Provides
    @Singleton
    fun provideGraphQLClient(
        apolloClient: ApolloClient,
        //FIXME: УБРАТЬ ЛОКАЛЬНОЕ СОХРАНЕНИЕ ИЗ РЕПОЗИТОРИЯ ДЛЯ АПИ
        tokenDataSource: TokenDataSource,
        taskRepository: TaskRepository,
        roleRepository: RoleRepository,
        organizationRepository: OrganizationRepository,
        projectRepository: ProjectRepository,
        userRepository: UserRepository,
        userOrgRepository: UserOrgRepository,
        userTaskRepository: UserTaskRepository,
        groupRepository: GroupRepository,
    ): GraphQLClient {
        return GraphQLClientImpl(
            apolloClient,
            //FIXME: УБРАТЬ ЛОКАЛЬНОЕ СОХРАНЕНИЕ ИЗ РЕПОЗИТОРИЯ ДЛЯ АПИ
            tokenDataSource,
            taskRepository,
            roleRepository,
            organizationRepository,
            projectRepository,
            userRepository,
            userOrgRepository,
            userTaskRepository,
            groupRepository
        )
    }
}