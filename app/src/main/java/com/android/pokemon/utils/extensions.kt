package com.android.pokemon.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide


fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 60f
        start()

    }
}

fun ImageView.load(url:String?, progressDrawable: CircularProgressDrawable) {
    Glide.with(this)
        .load(url)
        .placeholder(progressDrawable)
        .into(this)
}
