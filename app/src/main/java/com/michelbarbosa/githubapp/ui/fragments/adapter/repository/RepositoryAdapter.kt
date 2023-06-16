package com.michelbarbosa.githubapp.ui.fragments.adapter.repository

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.michelbarbosa.githubapp.model.GitRepositoryDomain

class RepositoryAdapter() :
    ListAdapter<GitRepositoryDomain, RepositoryViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepositoryViewHolder.create(parent)

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<GitRepositoryDomain>() {
                override fun areItemsTheSame(
                    oldItem: GitRepositoryDomain,
                    newItem: GitRepositoryDomain
                ): Boolean {
                    return oldItem === newItem
                }

                override fun areContentsTheSame(
                    oldItem: GitRepositoryDomain,
                    newItem: GitRepositoryDomain
                ): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}