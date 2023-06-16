package com.michelbarbosa.githubapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.michelbarbosa.githubapp.model.GitRepositoryDomain
import com.michelbarbosa.githubapp.model.UserDetailDomain
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.network.response.toDomain
import com.michelbarbosa.githubapp.network.response.toUserDetailDomain
import com.michelbarbosa.githubapp.network.response.toUserDomain
import com.michelbarbosa.githubapp.usecase.finduser.FindUserUseCase
import com.michelbarbosa.githubapp.usecase.getuserdetail.GetUserDetailUseCase
import com.michelbarbosa.githubapp.usecase.listrepositories.ListUserRepositoriesUseCase
import com.michelbarbosa.githubapp.usecase.listusers.ListUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val listUsersUseCase: ListUsersUseCase,
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val findUserUseCase: FindUserUseCase,
    private val listUserRepositoriesUseCase: ListUserRepositoriesUseCase
): ViewModel() {

    var userDetailDomain: UserDetailDomain? = null

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

    suspend fun findUser(
        charArray: CharSequence,
        onFindUserEmpty: () -> Unit,
        onFindUserSuccess: (data: PagingData<UserDomain>) -> Unit,
        ){
        findUserUseCase.invoke(charArray.toString()).let { findUserResponse ->
            if (findUserResponse.isSuccessful) {
                findUserResponse.body()?.let { userDetail ->
                    userDetailDomain = userDetail.toUserDetailDomain()
                    onFindUserSuccess.invoke(
                        PagingData.from(listOf(userDetail.toUserDomain()))
                    )
                } ?: kotlin.run {
                    onFindUserEmpty.invoke()
                }
            } else {
                onFindUserEmpty.invoke()
            }
        }
    }

    suspend fun filterUsers(
        matchItensFound: Int,
        charArray: CharSequence,
        onFilterData: (data: PagingData<UserDomain>) -> Unit,
        onFindUserSuccess: (data: PagingData<UserDomain>) -> Unit,
        onFindUserEmpty: () -> Unit
    ) {
        listUsers().collectLatest { pagingData ->
            if (matchItensFound > 0) {
                var userDomain: UserDomain? = null
                onFilterData.invoke(
                    pagingData.filter { user ->
                        userDomain = user
                        user.login.lowercase().contains(charArray)
                    }
                )

                userDomain?.let {
                    if (!it.login.lowercase().contains(charArray))
                        onFindUserEmpty.invoke()
                }

            } else {
                findUser(charArray, onFindUserEmpty, onFindUserSuccess)
            }
        }
    }

    suspend fun listRepositories(
        userName: String,
        onListRepositories: (List<GitRepositoryDomain>) -> Unit,
        onListEmpty: () -> Unit
    ) {
        listUserRepositoriesUseCase.invoke(userName).let { response ->
            if (response.isSuccessful) {
                response.body()?.let { list ->
                    onListRepositories.invoke(
                        list.map { itemResponse -> itemResponse.toDomain() }
                    )
                } ?: kotlin.run {
                    onListEmpty.invoke()
                }
            } else {
                onListEmpty.invoke()
            }
        }
    }

}