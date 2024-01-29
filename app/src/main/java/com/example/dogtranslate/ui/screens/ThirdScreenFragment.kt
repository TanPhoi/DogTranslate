package com.example.dogtranslate.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.dogtranslate.R

class ThirdScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third_srceen, container, false)
    }

    private fun cleanupView() {
        val imageView: ImageView? = view?.findViewById(R.id.imageView)
        imageView?.setOnClickListener(null)
    }

    override fun onDestroyView() {
        cleanupView()
        super.onDestroyView()
    }
}