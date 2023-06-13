package com.michelbarbosa.githubapp.ui.fragments.adapter.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michelbarbosa.githubapp.R
import com.michelbarbosa.githubapp.databinding.ItemRepositoryBinding
import com.michelbarbosa.githubapp.model.GitRepositoryDomain
import com.michelbarbosa.githubapp.utils.Util

class RepositoryViewHolder(
    itemBinding: ItemRepositoryBinding
): RecyclerView.ViewHolder(itemBinding.root) {

    private val context = itemBinding.root.context

    private val tName = itemBinding.tvName
    private val tLanguage = itemBinding.tvLanguage
    private val tForkCount = itemBinding.tvForkCount
    private val tCreatedAt = itemBinding.tvCreatedAt
    private val tUpdatedAt = itemBinding.tvUpdatedAt
    private val tPushedAt = itemBinding.tvPushedAt
    private val tDesc = itemBinding.tvDesc

    fun bind(repos: GitRepositoryDomain){
        tName.text = repos.name
        tLanguage.text = repos.language
        tForkCount.text = context.getString(R.string.repos_forks, repos.forkCount)
        tCreatedAt.text = context.getString(R.string.repos_created_at, Util.normalizePatternDate(repos.createdAt))
        tUpdatedAt.text = context.getString(R.string.repos_updated_at, Util.normalizePatternDate(repos.updatedAt))
        tPushedAt.text = context.getString(R.string.repos_created_at, Util.normalizePatternDate(repos.pushedAt))
        tDesc.text = repos.description
    }

    companion object{
        fun create(
            parent: ViewGroup
            ): RepositoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemRepositoryBinding.inflate(inflater, parent, false)
            return RepositoryViewHolder(itemBinding)
        }
    }

}