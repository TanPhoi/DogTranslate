package com.example.dogtranslate.ui.intro

import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.dogtranslate.R
import com.example.dogtranslate.databinding.FragmentIntroBinding
import com.example.dogtranslate.ui.base.BaseFragment
import java.lang.ref.WeakReference

class IntroFragment : BaseFragment<FragmentIntroBinding, IntroViewModel>() {
    private val fragmentReference = WeakReference(this)
    private var adapter: SliderPagerAdapter? = null
    private var indicator: Array<ImageView>? = null
    private var page: Int = 0
    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            page = position
            updateIndicatorPage(page)
        }
    }
    override fun layoutRes(): Int {
        return R.layout.fragment_intro
    }

    override fun viewModelClass(): Class<IntroViewModel> {
        return IntroViewModel::class.java
    }

    override fun initView() {
        updateIndicatorPage(0)
        handleBottomNavigation()
        setupViewPager()

        binding?.btnNext?.setOnClickListener {
            page++

            if (page < (adapter?.itemCount ?: 0)) {
                binding?.viewPager?.currentItem = page
            } else {
                findNavController().navigate(R.id.action_introFragment_to_homeFragment)
                viewModel?.saveFirstScreen(true)
            }
            updateIndicatorPage(page)
        }
    }

    private fun setupViewPager() {
        binding?.viewPager?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                page = position
                updateIndicatorPage(page)
            }
        })
    }

    private fun updateIndicatorPage(position: Int) {
        indicator?.let {
            for (i in it.indices) {
                it[i].setBackgroundResource(
                    if (i == position) R.drawable.bg_selected_indicator else R.drawable.bg_unselected_indicator
                )
            }
        }
    }

    private fun handleBottomNavigation() {
        indicator =
            arrayOf(binding!!.ivIndicatorOne, binding!!.ivIndicatorTwo, binding!!.ivIndicatorThree)
        adapter = SliderPagerAdapter(requireActivity())
        binding?.viewPager?.adapter = adapter
        binding?.viewPager?.currentItem = page
        binding?.viewPager?.clipToPadding = false
        binding?.viewPager?.clipChildren = false
        updateIndicatorPage(page)
    }

    override fun onDestroyView() {
        binding?.viewPager?.unregisterOnPageChangeCallback(pageChangeCallback)
        binding?.viewPager?.adapter = null
        binding?.relativeLayout?.removeAllViewsInLayout()
        adapter = null
        indicator = null
        binding = null
        viewModel = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        binding?.viewPager?.unregisterOnPageChangeCallback(pageChangeCallback)
        binding?.viewPager?.adapter = null
        binding?.relativeLayout?.removeAllViewsInLayout()
        binding?.unbind()
        adapter = null
        indicator = null
        binding = null
        viewModel = null
        super.onDestroy()
    }
}
