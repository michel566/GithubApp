package com.michelbarbosa.githubapp.di

import com.michelbarbosa.githubapp.model.HeaderPagingConfig
import com.michelbarbosa.githubapp.model.UserResultDomain
import com.michelbarbosa.githubapp.network.response.UsersResponse
import com.michelbarbosa.githubapp.paging.data.RemoteDataSource
import com.michelbarbosa.githubapp.remote.UserRemoteDataSourceImpl
import com.michelbarbosa.githubapp.repository.UserRepository
import com.michelbarbosa.githubapp.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindRemoteDataSource(datasourceImpl: UserRemoteDataSourceImpl): RemoteDataSource<UserResultDomain?>

}