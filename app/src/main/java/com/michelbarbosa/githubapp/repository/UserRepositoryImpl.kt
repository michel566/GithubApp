package com.michelbarbosa.githubapp.repository

import androidx.paging.PagingSource
import com.michelbarbosa.githubapp.model.UserDetailDomain
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.model.UserResultDomain
import com.michelbarbosa.githubapp.network.api.GithubApi
import com.michelbarbosa.githubapp.network.response.toUserDetailDomain
import com.michelbarbosa.githubapp.network.response.toUserDomain
import com.michelbarbosa.githubapp.paging.UserPagingSource
import com.michelbarbosa.githubapp.paging.data.RemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource<UserResultDomain?>,
    private val api: GithubApi
) : UserRepository {

    override fun listUsers(pages: Int): PagingSource<Int, UserDomain> =
        UserPagingSource(
            dataSource = remoteDataSource,
            perPage = pages
        )

    override suspend fun getUserDetail(userName: String) =
        api.getUserDetail(userName).toUserDetailDomain()

    override suspend fun findUser(userName: String) =
        api.findUser(userName)


}