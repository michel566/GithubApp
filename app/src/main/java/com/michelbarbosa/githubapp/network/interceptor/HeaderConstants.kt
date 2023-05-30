package com.michelbarbosa.githubapp.network.interceptor

object HeaderConstants {
    const val NAME_REQUEST_FILE = "Accept"
    const val TYPE_REQUEST_FILE = "application/vnd.github+json"
    const val NAME_TOKEN_FILE = "Authorization"

    fun concatToken (token: String) = "Bearer $token"
}