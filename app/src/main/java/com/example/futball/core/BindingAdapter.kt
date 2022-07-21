package com.example.futball.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.futball.R

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, p: String?) {
    val options = RequestOptions.placeholderOf(R.drawable.downloading).error(R.drawable.error)
    Glide.with(view).setDefaultRequestOptions(options).load(p ?: "").into(view)
}