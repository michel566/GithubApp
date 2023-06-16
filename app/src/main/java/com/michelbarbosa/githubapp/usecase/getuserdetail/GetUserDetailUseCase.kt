package com.michelbarbosa.githubapp.usecase.getuserdetail

import com.michelbarbosa.githubapp.model.UserDetailDomain

interface GetUserDetailUseCase {
    suspend fun invoke(userName: String): UserDetailDomain
}