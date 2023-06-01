package com.michelbarbosa.githubapp.remote

import com.michelbarbosa.githubapp.model.HeaderPagingConfig
import com.michelbarbosa.githubapp.model.HeaderParam.PARAM_LINK
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.model.UserResultDomain
import com.michelbarbosa.githubapp.network.api.GithubApi
import com.michelbarbosa.githubapp.network.api.GithubApi.Companion.LIST_USERS_ENDPOINT
import com.michelbarbosa.githubapp.network.response.UsersResponse
import com.michelbarbosa.githubapp.paging.data.RemoteDataSource
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val api: GithubApi
) : RemoteDataSource<UserResultDomain?> {

    override suspend fun listUsers(since: Int, perPage: Int): UserResultDomain? {
        val usersResponse = UsersResponse(users = listOf())

        var result: UserResultDomain? = null

        val response = api.listUsers(since, perPage)

        usersResponse.users = response.body()
            ?: kotlin.run { listOf() }

        response.headers().let { headers ->
            result = usersResponse.users.map {
                UserDomain(it.id, it.login, it.avatar_url)
            }.let { listDomain ->
                UserResultDomain(
                    pagingConfig = HeaderPagingConfig(
                        link = headers[PARAM_LINK],
                        endpoint = LIST_USERS_ENDPOINT
                    ),
                    listUser = listDomain
                )
            }
        }

        return result
    }
}