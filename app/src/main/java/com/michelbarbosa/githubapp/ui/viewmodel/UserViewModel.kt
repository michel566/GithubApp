package com.michelbarbosa.githubapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.usecase.finduser.FindUserUseCase
import com.michelbarbosa.githubapp.usecase.getuserdetail.GetUserDetailUseCase
import com.michelbarbosa.githubapp.usecase.listusers.ListUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val listUsersUseCase: ListUsersUseCase,
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val findUserUseCase: FindUserUseCase
): ViewModel() {

    fun listUsers(): Flow<PagingData<UserDomain>> =
        listUsersUseCase.invoke(
            ListUsersUseCase.ListUsersParams(
                pagingConfig = PagingConfig(
                    pageSize = 10
                )
            )
        )
            .cachedIn(viewModelScope)

    suspend fun getUserDetail(userName: String) =
        getUserDetailUseCase.invoke(userName)

    suspend fun filterUsers(
        matchItensFound: Int,
        charArray: CharSequence,
        onFilterData: (data: PagingData<UserDomain>) -> Unit,
        onEmptyData: (data: PagingData<UserDomain>) -> Unit
    ) {
        listUsers().collectLatest { pagingData ->
            if (matchItensFound > 0) {
                onFilterData.invoke(
                    pagingData.filter { user ->
                        user.login.lowercase().contains(charArray)
                    }
                )
            } else {
                onEmptyData.invoke(
                    PagingData.from(listOf(findUserUseCase.invoke(charArray.toString())))
                )
            }
        }
    }

}