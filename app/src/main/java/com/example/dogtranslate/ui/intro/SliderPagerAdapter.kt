package com.example.dogtranslate.ui.intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dogtranslate.ui.screens.FirstScreensFragment
import com.example.dogtranslate.ui.screens.SecondScreenFragment
import com.example.dogtranslate.ui.screens.ThirdScreenFragment

class SliderPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstScreensFragment()
            1 -> SecondScreenFragment()
            2 -> ThirdScreenFragment()
            else -> FirstScreensFragment()
        }
    }
}