package com.example.dogtranslate.util

import android.view.View
import com.example.dogtranslate.listener.OnSingleClickListener

fun View.setOnSingClickListener(onClick: (View) -> Unit) {
    setOnClickListener(object : OnSingleClickListener() {
        override fun onSingleClick(view: View) {
            onClick.invoke(view)
        }
    })
}