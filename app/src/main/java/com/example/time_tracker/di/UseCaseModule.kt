package com.example.time_tracker.di

import com.example.time_tracker.domain.network.GraphQLClient
import com.example.time_tracker.domain.useCase.AddOrganizationUseCase
import com.example.time_tracker.domain.useCase.AddRoleUseCase
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
}