package com.example.dogtranslate.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.example.dogtranslate.R
import com.example.dogtranslate.databinding.LayoutActionBarHeaderCommonBinding
import com.example.dogtranslate.util.setOnSingClickListener

class ActionBarHeaderCommon(context: Context?, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {
    private val binding: LayoutActionBarHeaderCommonBinding

    init {
        binding = LayoutActionBarHeaderCommonBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)

        val typeArray = context?.obtainStyledAttributes(attrs, R.styleable.ActionBarHeaderCommon)

        val srcOne = typeArray?.getResourceId(R.styleable.ActionBarHeaderCommon_src_one, -1) ?: -1
        if (srcOne != -1) {
            binding.imageView.setImageDrawable(ContextCompat.getDrawable(context!!, srcOne))
        }

        val enableSrcOne =
            typeArray?.getBoolean(R.styleable.ActionBarHeaderCommon_src_one_enable, false) ?: false
        binding.imageView.visibility = if (enableSrcOne) View.VISIBLE else View.GONE

        val textOne = typeArray?.getString(R.styleable.ActionBarHeaderCommon_text_view_one) ?: ""
        binding.textView.text = textOne

        val enableTextOne =
            typeArray?.getBoolean(R.styleable.ActionBarHeaderCommon_text_view_one_enable, false)
                ?: false
        binding.textView.visibility = if (enableTextOne) View.VISIBLE else View.GONE

        typeArray?.recycle()

    }

    fun setOnClickOpenMenu(callbacks: (() -> Unit)) {
        binding.imageView.setOnSingClickListener {
            callbacks.invoke()
        }
    }

    fun setTitle(title: String) {
        binding.textView.text = title
    }

    fun setOnClickBack(callbacks: (() -> Unit)) {
        binding.imageView.setOnSingClickListener {
            callbacks.invoke()
        }
    }
}