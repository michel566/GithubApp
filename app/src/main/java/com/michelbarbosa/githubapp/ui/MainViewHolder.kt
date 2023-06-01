package com.michelbarbosa.githubapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michelbarbosa.githubapp.databinding.ItemMainBinding
import com.michelbarbosa.githubapp.model.UserDomain

class MainViewHolder(
    itemBinding: ItemMainBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    private val text = itemBinding.tvItem
    fun bind(user: UserDomain) {
        text.text = user.login
    }
    companion object {
        fun create(parent: ViewGroup): MainViewHolder {
            val itemBinding = ItemMainBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return MainViewHolder(itemBinding)
        }
    }

}


