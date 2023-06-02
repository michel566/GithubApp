package com.michelbarbosa.githubapp.network.response

import com.google.gson.annotations.SerializedName
import com.michelbarbosa.githubapp.model.UserDetailDomain
import com.michelbarbosa.githubapp.model.UserDomain

data class UserDetail(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val node_id: String,
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("gravatar_id")
    val gravatar_id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("html_url")
    val html_url: String,
    @SerializedName("followers_url")
    val followers_url: String,
    @SerializedName("following_url")
    val following_url: String,
    @SerializedName("gists_url")
    val gists_url: String,
    @SerializedName("starred_url")
    val starred_url: String,
    @SerializedName("subscriptions_url")
    val subscriptions_url: String,
    @SerializedName("organizations_url")
    val organizations_url: String,
    @SerializedName("repos_url")
    val repos_url: String,
    @SerializedName("events_url")
    val events_url: String,
    @SerializedName("received_events_url")
    val received_events_url: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("site_admin")
    val site_admin: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("blog")
    val blog: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("email")
    val email: String?,
    @SerializedName("hireable")
    val hireable: Boolean?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("twitter_username")
    val twitter_username: String?,
    @SerializedName("public_repos")
    val public_repos: Int,
    @SerializedName("public_gists")
    val public_gists: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("updated_at")
    val updated_at: String,
)

fun UserDetail.toUserDetailDomain(): UserDetailDomain =
    UserDetailDomain(
        id = this.id,
        login = this.login,
        avatarUrl = this.avatar_url,
        name = this.name,
        company = this.company,
        location = this.location,
        email = this.email,
        bio = this.bio,
        twitterUsername = this.twitter_username,
        publicRepos = this.public_repos,
        publicGists = this.public_gists,
        followers = this.followers,
        createdAt = this.created_at,
        updatedAt = this.updated_at
    )


fun UserDetail.toUserDomain(): UserDomain =
    UserDomain(
        id = this.id,
        login = this.login,
        avatarUrl = this.avatar_url
    )
