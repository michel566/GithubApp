package com.michelbarbosa.githubapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetailDomain(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val name: String,
    val company: String,
    val location: String,
    val email: String,
    val bio: String,
    val twitterUsername: String,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val createdAt: String,
    val updatedAt: String
): Parcelable