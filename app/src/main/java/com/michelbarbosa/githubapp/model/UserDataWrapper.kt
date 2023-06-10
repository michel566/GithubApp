package com.michelbarbosa.githubapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataWrapper(
    val login: String,
    val userDetail: UserDetailDomain?
) : Parcelable