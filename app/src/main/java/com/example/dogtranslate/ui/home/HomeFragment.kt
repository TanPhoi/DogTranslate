package com.example.dogtranslate.ui.home

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.example.dogtranslate.ui.base.BaseFragment
import com.example.dogtranslate.R
import com.example.dogtranslate.data.local.model.Rating
import com.example.dogtranslate.databinding.DialogRatingAppBinding
import com.example.dogtranslate.databinding.FragmentHomeBinding
import com.example.dogtranslate.databinding.HeaderDrawLayoutBinding
import com.example.dogtranslate.ui.base.BaseActivity
import com.example.dogtranslate.util.setOnSingClickListener
import com.example.dogtranslate.util.shareApp

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private var adapter: UserNavigationAdapter? = null
    private var bindingDialog: DialogRatingAppBinding? = null
    private var bindingMenu: HeaderDrawLayoutBinding? = null
    private var isRatingScore = 0

    override fun layoutRes(): Int = R.layout.fragment_home

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        handleBottomNavigation()
        handleDrawer()
        handleMenuNavigation()
    }

    private fun handleDrawer() {
        binding?.abUserNavigation?.setOnClickOpenMenu {
            if (binding?.drawerLayout?.isDrawerOpen(GravityCompat.START) == false) {
                binding?.drawerLayout?.openDrawer(GravityCompat.START)
            } else {
                binding?.drawerLayout?.closeDrawer(GravityCompat.START)
            }
        }
    }

    private fun handleMenuNavigation() {
        bindingMenu = HeaderDrawLayoutBinding.inflate(layoutInflater)
        if (viewModel?.getRating()!!) {
            bindingMenu?.llRate?.visibility = View.GONE
        } else {
            bindingMenu?.llRate?.visibility = View.VISIBLE
        }

        bindingMenu?.llLanguage?.setOnSingClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_languageFragment)
            binding?.drawerLayout?.close()
        }

        bindingMenu?.llShare?.setOnSingClickListener {
            shareApp(requireContext())
            binding?.drawerLayout?.close()
        }

        bindingMenu?.llRate?.setOnSingClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            bindingDialog = DialogRatingAppBinding.inflate(layoutInflater)
            builder.setView(bindingDialog?.root!!)
            val alertDialog = builder.create()
            if (alertDialog.window != null) {
                alertDialog?.window?.setBackgroundDrawable(ColorDrawable(0))
            }

            bindingDialog?.rtb?.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

                when (ratingBar.rating.toInt()) {
                    1 -> {
                        bindingDialog?.tvTitle?.text = resources.getString(R.string.label_oh_no)
                        bindingDialog?.tvContent?.text =
                            resources.getString(R.string.lablel_please_leave_as_some_feedback)
                        isRatingScore = ratingBar.rating.toInt()
                    }

                    2 -> {
                        bindingDialog?.tvTitle?.text = resources.getString(R.string.label_oh_no)
                        bindingDialog?.tvContent?.text =
                            resources.getString(R.string.lablel_please_leave_as_some_feedback)
                        isRatingScore = ratingBar.rating.toInt()
                    }

                    3 -> {
                        bindingDialog?.tvTitle?.text =
                            resources.getString(R.string.label_thanks_for_your_rating)
                        bindingDialog?.tvContent?.text =
                            resources.getString(R.string.lablel_please_leave_as_some_feedback)
                        isRatingScore = ratingBar.rating.toInt()
                    }

                    4 -> {
                        bindingDialog?.tvTitle?.text =
                            resources.getString(R.string.label_we_like_you_too)
                        bindingDialog?.tvContent?.text =
                            resources.getString(R.string.label_thanks_for_your_rating)
                        isRatingScore = ratingBar.rating.toInt()
                    }

                    5 -> {
                        bindingDialog?.tvTitle?.text =
                            resources.getString(R.string.label_we_like_you_too)
                        bindingDialog?.tvContent?.text =
                            resources.getString(R.string.label_thanks_for_your_rating)
                        isRatingScore = ratingBar.rating.toInt()
                    }

                    else -> {
                        bindingDialog?.tvTitle?.text =
                            resources.getString(R.string.we_are_working_hard_for_a_better_user_experience)
                        bindingDialog?.tvContent?.text =
                            resources.getString(R.string.we_d_greatly_appreciate_if_you_can_rate_us)
                    }
                }
            }

            bindingDialog?.textBest?.setOnSingClickListener {
                toggleFeedbackVisibility()
            }

            bindingDialog?.btnMaybeLater?.setOnSingClickListener {
                alertDialog?.dismiss()
                binding?.drawerLayout?.close()
            }

            bindingDialog?.btnRate?.setOnSingClickListener {
                val stringComment = bindingDialog?.editFeedback?.text.toString().trim()
                val newRating = Rating().apply {
                    ratingScore = isRatingScore
                    if (stringComment.isNotEmpty()) {
                        comment = stringComment
                    } else {
                        comment = ""
                    }
                    timestamp = System.currentTimeMillis()
                }

                viewModel?.insertRating(newRating)
                viewModel?.saveRatingPreference(true)
                alertDialog.dismiss()
                binding?.drawerLayout?.close()
            }

            alertDialog.show()
        }

        bindingMenu?.llExit?.setOnSingClickListener {
            activity?.finish()
        }

        binding?.navView?.addHeaderView(bindingMenu?.root!!)
    }

    private fun toggleFeedbackVisibility() {
        if (bindingDialog?.editFeedback?.visibility == View.VISIBLE) {
            bindingDialog?.editFeedback?.visibility = View.GONE
        } else {
            bindingDialog?.editFeedback?.visibility = View.VISIBLE
        }
    }

    private fun handleBottomNavigation() {
        adapter = UserNavigationAdapter(requireActivity())
        binding?.vpData?.adapter = adapter
        binding?.vpData?.isUserInputEnabled = false

        binding?.nvbDogTranslate?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.sounds -> binding?.vpData?.currentItem = 0
                R.id.translator -> binding?.vpData?.currentItem = 1
                R.id.training -> binding?.vpData?.currentItem = 2
                R.id.whistle -> binding?.vpData?.currentItem = 3
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onStart() {
        super.onStart()
        handleBottomNavigation()
    }

    override fun onDestroyView() {
        binding?.drawerLayout?.removeAllViewsInLayout()
        bindingDialog = null
        bindingMenu = null
        binding = null
        super.onDestroyView()
        binding?.vpData?.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
    }
}