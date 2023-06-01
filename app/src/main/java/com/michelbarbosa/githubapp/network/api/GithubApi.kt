package com.michelbarbosa.githubapp.network.api

import com.michelbarbosa.githubapp.model.HeaderParam.PARAM_SINCE
import com.michelbarbosa.githubapp.network.response.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    companion object {
        const val LIST_USERS_ENDPOINT = "users"
    }

    @GET(LIST_USERS_ENDPOINT)
    suspend fun listUsers(
        @Query(PARAM_SINCE) since: Int,
        @Query("per_page") perPage: Int
    ): Response<List<User>>

}