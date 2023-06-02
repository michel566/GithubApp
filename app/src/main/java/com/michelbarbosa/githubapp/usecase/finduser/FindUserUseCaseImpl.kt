package com.michelbarbosa.githubapp.usecase.finduser

import com.michelbarbosa.githubapp.repository.UserRepository
import javax.inject.Inject

class FindUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): FindUserUseCase {

    override suspend fun invoke(userName: String) =
        repository.findUser(userName)

}