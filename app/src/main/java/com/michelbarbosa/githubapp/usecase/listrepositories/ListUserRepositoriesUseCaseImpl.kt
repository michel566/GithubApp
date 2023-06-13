package com.michelbarbosa.githubapp.usecase.listrepositories

import com.michelbarbosa.githubapp.repository.UserRepository
import javax.inject.Inject

class ListUserRepositoriesUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : ListUserRepositoriesUseCase {

    override suspend fun invoke(userName: String) =
        repository.listUserRepositories(userName)
}