package com.michelbarbosa.githubapp.repository

import androidx.paging.PagingSource
import com.michelbarbosa.githubapp.model.UserDomain
interface UserRepository {
    fun listUsers(pages: Int): PagingSource<Int, UserDomain>
}