package com.michelbarbosa.githubapp.network.api

import android.database.Observable
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.network.response.User
import com.michelbarbosa.githubapp.network.response.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("users")
    suspend fun listUsers(
//        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): Response<List<User>>

}