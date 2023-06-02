package com.michelbarbosa.githubapp.ui.callbacks

import com.michelbarbosa.githubapp.ui.fragments.UserFragment

interface MainCallback {
    fun onAttachedUserFragment(fragment: UserFragment)
}