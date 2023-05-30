package com.michelbarbosa.githubapp.paging.data

interface RemoteDataSource<T> {
    suspend fun listUsers(since: Int, perPage: Int) : T
}