package com.michelbarbosa.githubapp.network.api

import com.michelbarbosa.githubapp.model.HeaderParam.PARAM_SINCE
import com.michelbarbosa.githubapp.network.response.User
import com.michelbarbosa.githubapp.network.response.UserDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    companion object {
        const val USER_ENDPOINT = "users"
    }

    @GET(USER_ENDPOINT)
    suspend fun listUsers(
        @Query(PARAM_SINCE) since: Int,
        @Query("per_page") perPage: Int
    ): Response<List<User>>

    @GET("$USER_ENDPOINT/{username}")
    suspend fun getUser(
        @Path("username") userName: String
    ): UserDetail

}