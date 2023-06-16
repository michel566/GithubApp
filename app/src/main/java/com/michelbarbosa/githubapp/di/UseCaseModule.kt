package com.michelbarbosa.githubapp.di

import com.michelbarbosa.githubapp.usecase.finduser.FindUserUseCase
import com.michelbarbosa.githubapp.usecase.finduser.FindUserUseCaseImpl
import com.michelbarbosa.githubapp.usecase.getuserdetail.GetUserDetailUseCase
import com.michelbarbosa.githubapp.usecase.getuserdetail.GetUserDetailUseCaseImpl
import com.michelbarbosa.githubapp.usecase.listrepositories.ListUserRepositoriesUseCase
import com.michelbarbosa.githubapp.usecase.listrepositories.ListUserRepositoriesUseCaseImpl
import com.michelbarbosa.githubapp.usecase.listusers.ListUsersUseCase
import com.michelbarbosa.githubapp.usecase.listusers.ListUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindListUsersUseCase(listUsersUseCaseImpl: ListUsersUseCaseImpl) : ListUsersUseCase

    @Binds
    fun bindFindUserUseCase(findUserUseCaseImpl: FindUserUseCaseImpl): FindUserUseCase

    @Binds
    fun bindGetUserDetailUseCase(getUserDetailUseCaseImpl: GetUserDetailUseCaseImpl): GetUserDetailUseCase

    @Binds
    fun bindListUserRepositoriesUseCase(listUserRepositoriesUseCaseImpl: ListUserRepositoriesUseCaseImpl): ListUserRepositoriesUseCase

}