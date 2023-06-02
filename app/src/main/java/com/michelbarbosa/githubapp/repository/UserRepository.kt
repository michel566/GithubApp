package com.michelbarbosa.githubapp.repository

import androidx.paging.PagingSource
import com.michelbarbosa.githubapp.model.UserDetailDomain
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.network.response.UserDetail

interface UserRepository {
    fun listUsers(pages: Int): PagingSource<Int, UserDomain>
    suspend fun getUserDetail(userName: String): UserDetailDomain
    suspend fun findUser(userName: String): UserDomain
}