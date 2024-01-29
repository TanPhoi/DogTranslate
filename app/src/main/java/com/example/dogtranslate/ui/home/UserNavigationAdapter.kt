package com.example.dogtranslate.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dogtranslate.ui.home.HomeFragment
import com.example.dogtranslate.ui.sounds.SoundsFragment
import com.example.dogtranslate.ui.training.TrainingFragment
import com.example.dogtranslate.ui.translator.TranslatorFragment
import com.example.dogtranslate.ui.whistle.WhistleFragment

class UserNavigationAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SoundsFragment()
            1 -> TranslatorFragment()
            2 -> TrainingFragment()
            3 -> WhistleFragment()
            else -> SoundsFragment()
        }
    }
}