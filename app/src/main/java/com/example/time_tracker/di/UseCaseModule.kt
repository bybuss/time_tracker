package com.example.time_tracker.di

import com.example.time_tracker.domain.network.GraphQLClient
import com.example.time_tracker.domain.useCase.AddOrganizationUseCase
import com.example.time_tracker.domain.useCase.AddProjectUseCase
import com.example.time_tracker.domain.useCase.AddRoleUseCase
import com.example.time_tracker.domain.useCase.AddTaskUseCase
import com.example.time_tracker.domain.useCase.AddUserUseCase
import com.example.time_tracker.domain.useCase.AuthUserUseCase
import com.example.time_tracker.domain.useCase.GetAllFullTasksByAssignerIdUseCase
import com.example.time_tracker.domain.useCase.GetAllSimpleTasksByAssignerIdUseCase
import com.example.time_tracker.domain.useCase.GetFullTaskByIdUseCase
import com.example.time_tracker.domain.useCase.GetSimpleTaskByIdUseCase
import com.example.time_tracker.domain.useCase.RefreshTokenUseCase
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
    fun provideAddRoleUseCase(graphQLClient: GraphQLClient): AddRoleUseCase
        = AddRoleUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideAddOrganizationUseCase(graphQLClient: GraphQLClient): AddOrganizationUseCase
        = AddOrganizationUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideAddUserUseCase(graphQLClient: GraphQLClient): AddUserUseCase
            = AddUserUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideAuthUserUseCase(graphQLClient: GraphQLClient): AuthUserUseCase
            = AuthUserUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideRefreshTokenUseCase(graphQLClient: GraphQLClient): RefreshTokenUseCase
            = RefreshTokenUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideAddProjectUseCase(graphQLClient: GraphQLClient): AddProjectUseCase
            = AddProjectUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideAddTaskUseCase(graphQLClient: GraphQLClient): AddTaskUseCase
            = AddTaskUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideGetAllFullTasksByAssignerIdUseCase(graphQLClient: GraphQLClient): GetAllFullTasksByAssignerIdUseCase
            = GetAllFullTasksByAssignerIdUseCase(graphQLClient)
    @Provides
    @Singleton
    fun provideGetFullTaskByIdUseCase(graphQLClient: GraphQLClient): GetFullTaskByIdUseCase
            = GetFullTaskByIdUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideGetAllSimpleTasksByAssignerIdUseCase(graphQLClient: GraphQLClient): GetAllSimpleTasksByAssignerIdUseCase
            = GetAllSimpleTasksByAssignerIdUseCase(graphQLClient)

    @Provides
    @Singleton
    fun provideGetSimpleTaskByIdUseCase(graphQLClient: GraphQLClient): GetSimpleTaskByIdUseCase
            = GetSimpleTaskByIdUseCase(graphQLClient)
}