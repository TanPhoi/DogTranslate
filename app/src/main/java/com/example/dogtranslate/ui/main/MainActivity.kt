package com.example.dogtranslate.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogtranslate.R
import com.example.dogtranslate.databinding.ActivityMainBinding
import com.example.dogtranslate.ui.base.BaseActivity
import com.example.dogtranslate.ui.language.LocaleHelper

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun layoutRes(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {
        LocaleHelper.setLocale(this, viewModel.getLanguagePreference())
    }

    override fun onDestroy() {
        super.onDestroy()
        this.setSupportActionBar(null)
    }
}