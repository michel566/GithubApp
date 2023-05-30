package com.michelbarbosa.githubapp.repository

import androidx.paging.PagingSource
import com.michelbarbosa.githubapp.model.HeaderPagingConfig
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.network.response.UsersResponse
import com.michelbarbosa.githubapp.paging.UserPagingSource
import com.michelbarbosa.githubapp.paging.data.RemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource<UsersResponse>
) : UserRepository {

    override fun listUsers(pages: Int): PagingSource<Int, UserDomain>{
        return UserPagingSource(
            dataSource = remoteDataSource,
//            pagingConfig = HeaderPagingConfig("", ""),
            perPage = pages
        )
    }

}