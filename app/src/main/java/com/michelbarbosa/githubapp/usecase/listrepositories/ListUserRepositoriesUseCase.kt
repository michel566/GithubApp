package com.michelbarbosa.githubapp.usecase.listrepositories

import com.michelbarbosa.githubapp.network.response.GitRepository
import retrofit2.Response

interface ListUserRepositoriesUseCase {
    suspend fun invoke(userName: String): Response<List<GitRepository>>
}