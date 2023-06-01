package com.michelbarbosa.githubapp.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ListUsersUseCase {
    operator fun invoke(params: ListUsersParams): Flow<PagingData<UserDomain>>
    data class ListUsersParams(val pagingConfig: PagingConfig)
}

class ListUsersUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : PagingUseCase<ListUsersUseCase.ListUsersParams, UserDomain>(), ListUsersUseCase {

    override fun createFlowObservable(params: ListUsersUseCase.ListUsersParams): Flow<PagingData<UserDomain>> {
        return Pager(config = params.pagingConfig) {
            repository.listUsers(pages = params.pagingConfig.pageSize)
        }.flow
    }

}