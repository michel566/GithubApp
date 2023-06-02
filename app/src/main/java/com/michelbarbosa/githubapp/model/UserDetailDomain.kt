package com.michelbarbosa.githubapp.model

data class UserDetailDomain(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val name: String,
    val company: String = "",
    val location: String,
    val email: String? = "",
    val bio: String? = "",
    val twitterUsername: String? = "",
    val publicRepos: Int = 0,
    val publicGists: Int = 0,
    val followers: Int = 0,
    val createdAt: String,
    val updatedAt: String
)