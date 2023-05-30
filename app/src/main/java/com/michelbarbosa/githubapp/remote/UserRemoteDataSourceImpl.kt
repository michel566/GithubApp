package com.michelbarbosa.githubapp.remote

import com.michelbarbosa.githubapp.network.api.GithubApi
import com.michelbarbosa.githubapp.network.response.UsersResponse
import com.michelbarbosa.githubapp.paging.data.RemoteDataSource
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val api: GithubApi
) : RemoteDataSource<UsersResponse> {

    override suspend fun listUsers(since: Int, perPage: Int): UsersResponse {
        val data = UsersResponse(users = listOf())
        val response = api.listUsers(perPage)
        val header = response.headers()

        data.users = response.body()
            ?: kotlin.run { listOf() }

        return data

    }
}