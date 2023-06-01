package com.michelbarbosa.githubapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.michelbarbosa.githubapp.R

object UiUtils {

    fun setupImage(context: Context, srcImage: String, view: ImageView){
        Glide.with(context)
            .load(srcImage)
            .centerCrop()
            .fallback(R.drawable.baseline_broken)
            .into(view)
    }

}