package com.michelbarbosa.githubapp.repository

import androidx.paging.PagingSource
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.model.UserResultDomain
import com.michelbarbosa.githubapp.paging.UserPagingSource
import com.michelbarbosa.githubapp.paging.data.RemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource<UserResultDomain?>
) : UserRepository {

    override fun listUsers(pages: Int): PagingSource<Int, UserDomain> =
        UserPagingSource(
            dataSource = remoteDataSource,
            perPage = pages
        )

}