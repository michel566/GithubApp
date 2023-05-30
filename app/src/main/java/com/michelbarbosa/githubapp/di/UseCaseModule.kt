package com.michelbarbosa.githubapp.di

import com.michelbarbosa.githubapp.usecase.ListUsersUseCase
import com.michelbarbosa.githubapp.usecase.ListUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindListUsersUseCase(listUsersUseCaseImpl: ListUsersUseCaseImpl) : ListUsersUseCase

}