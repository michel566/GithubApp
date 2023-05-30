package com.michelbarbosa.githubapp.model

data class UserResultDomain(
    val pagingConfig: HeaderPagingConfig,
    val listUser: List<UserDomain>
)