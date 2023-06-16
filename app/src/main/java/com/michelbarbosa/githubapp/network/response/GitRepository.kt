package com.michelbarbosa.githubapp.network.response

import com.google.gson.annotations.SerializedName
import com.michelbarbosa.githubapp.model.GitRepositoryDomain
import com.michelbarbosa.githubapp.model.UserDetailDomain

data class GitRepository(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val node_id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("full_name")
    val full_name: String?,
    @SerializedName("private")
    val private: String?,
    @SerializedName("owner")
    val owner: User,
    @SerializedName("description")
    val description: String?,
    @SerializedName("fork")
    val fork: Boolean?,
    @SerializedName("created_at")
    val created_at: String?,
    @SerializedName("updated_at")
    val updated_at: String?,
    @SerializedName("pushed_at")
    val pushed_at: String?,
    @SerializedName("git_url")
    val git_url: String?,
    @SerializedName("ssh_url")
    val ssh_url: String?,
    @SerializedName("clone_url")
    val clone_url: String?,
    @SerializedName("svn_url")
    val svn_url: String?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("stargazers_count")
    val stargazers_count: Int?,
    @SerializedName("watchers_count")
    val watchers_count: Int?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("has_issues")
    val has_issues: Boolean?,
    @SerializedName("has_projects")
    val has_projects: Boolean?,
    @SerializedName("has_downloads")
    val has_downloads: Boolean?,
    @SerializedName("has_wiki")
    val has_wiki: Boolean?,
    @SerializedName("has_pages")
    val has_pages: Boolean?,
    @SerializedName("has_discussions")
    val has_discussions: Boolean?,
    @SerializedName("forks_count")
    val forks_count: Int?,
    @SerializedName("mirror_url")
    val mirror_url: String?,
    @SerializedName("archived")
    val archived: Boolean?,
    @SerializedName("disabled")
    val disabled: Boolean?,
    @SerializedName("open_issues_count")
    val open_issues_count: Int?,
    @SerializedName("license")
    val license: License?,
    @SerializedName("allow_forking")
    val allow_forking: Boolean?,
    @SerializedName("is_template")
    val is_template: Boolean?,
    @SerializedName("web_commit_signoff_required")
    val web_commit_signoff_required: Boolean?,
    @SerializedName("topics")
    val topics: List<String>?,
    @SerializedName("visibility")
    val visibility: String?,
    @SerializedName("forks")
    val forks: Int?,
    @SerializedName("open_issues")
    val open_issues: Int?,
    @SerializedName("watchers")
    val watchers: Int?,
    @SerializedName("default_branch")
    val default_branch: String?
)

data class License(
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("spdx_id")
    val spdx_id: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("node_id")
    val node_id: String?,
)


fun GitRepository.toDomain(): GitRepositoryDomain =
    GitRepositoryDomain(
        id = this.id,
        name = this.name ?: "",
        fullName = this.full_name ?: "",
        createdAt = this.created_at ?: "",
        updatedAt = this.updated_at ?: "",
        pushedAt = this.pushed_at ?: "",
        description = this.description ?: "",
        language = this.language ?: "",
        forkCount = this.forks_count ?: 0
    )
