package com.michelbarbosa.githubapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.usecase.ListUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val listUsersUseCase: ListUsersUseCase
): ViewModel(){

    fun listUsers(): Flow<PagingData<UserDomain>> =
        listUsersUseCase.invoke(ListUsersUseCase.ListUsersParams(pagingConfig = PagingConfig(pageSize = 10)))

}