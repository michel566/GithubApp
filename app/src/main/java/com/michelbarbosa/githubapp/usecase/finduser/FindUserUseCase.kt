package com.michelbarbosa.githubapp.usecase.finduser

import com.michelbarbosa.githubapp.model.UserDomain

interface FindUserUseCase {
    suspend fun invoke(userName: String): UserDomain
}