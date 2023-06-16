package com.michelbarbosa.githubapp.ui.fragments.adapter.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michelbarbosa.githubapp.databinding.ItemUserBinding
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.utils.UiUtil

class UserViewHolder(
    itemBinding: ItemUserBinding,
    private val userCallback: (user: UserDomain) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    private val text = itemBinding.tvItem
    private val image = itemBinding.ivImage

    fun bind(user: UserDomain) {
        UiUtil.setupImage(itemView.context, user.avatarUrl, image)
        text.text = user.login

        itemView.setOnClickListener {
            userCallback.invoke(user)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            userCallback: (user: UserDomain) -> Unit
        ): UserViewHolder {
            val itemBinding = ItemUserBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return UserViewHolder(itemBinding, userCallback)
        }
    }

}


