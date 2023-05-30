package com.michelbarbosa.githubapp.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.michelbarbosa.githubapp.model.UserDomain

class MainAdapter() : PagingDataAdapter<UserDomain, MainViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainViewHolder.create(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UserDomain>() {
            override fun areItemsTheSame(oldItem: UserDomain, newItem: UserDomain): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserDomain, newItem: UserDomain): Boolean {
                return oldItem == newItem
            }
        }
    }
}