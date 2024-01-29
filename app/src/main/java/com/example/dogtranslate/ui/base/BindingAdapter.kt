package com.example.dogtranslate.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dogtranslate.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["image_url", "image_aspect_ratio"], requireAll = false)
    fun imageUrl(imageView: ImageView, imageUrl: String?, imageAspectRatio: Float?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_avatar_user)
                    .fitCenter()
            )
            .into(imageView)
    }
}