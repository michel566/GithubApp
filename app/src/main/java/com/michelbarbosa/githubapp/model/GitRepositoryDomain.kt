package com.michelbarbosa.githubapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitRepositoryDomain(
    val id: Int,
    val name: String,
    val fullName: String,
    val createdAt: String,
    val updatedAt: String,
    val pushedAt: String,
    val description: String,
    val language: String,
    val forkCount: Int,
) : Parcelable
