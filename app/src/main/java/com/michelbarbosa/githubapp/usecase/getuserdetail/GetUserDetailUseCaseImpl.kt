package com.michelbarbosa.githubapp.usecase.getuserdetail

import com.michelbarbosa.githubapp.repository.UserRepository
import javax.inject.Inject

class GetUserDetailUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetUserDetailUseCase {

    override suspend fun invoke(userName: String) =
        repository.getUserDetail(userName)
}