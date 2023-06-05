package com.michelbarbosa.githubapp.usecase.finduser

import com.michelbarbosa.githubapp.network.response.UserDetail
import retrofit2.Response

interface FindUserUseCase {
    suspend fun invoke(userName: String): Response<UserDetail>
}