package com.example.dogtranslate.ui.splash

import android.os.CountDownTimer
import androidx.navigation.fragment.findNavController
import com.example.dogtranslate.ui.base.BaseFragment
import com.example.dogtranslate.R
import com.example.dogtranslate.databinding.FragmentSplashBinding
import java.lang.ref.WeakReference

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    companion object {
        const val TIMER_DURATION = 5000L
        const val TIMER_COUNT_DOWN = 1000L
    }

    private var countIndex = 0
    private var countDownTimer: WeakReference<CountDownTimer>? = null

    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun viewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun initView() {
        handleProgressBar()
    }

    private fun handleProgressBar() {
        countDownTimer = WeakReference(object : CountDownTimer(TIMER_DURATION, TIMER_COUNT_DOWN) {
            override fun onTick(p0: Long) {
                countIndex++
                binding?.progressBar?.progress =
                    (countIndex * 100) / (TIMER_DURATION / TIMER_COUNT_DOWN).toInt()
            }

            override fun onFinish() {
                countIndex++
                binding?.progressBar?.progress = 100
                if (viewModel?.getFirstScreen() == true) {
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_introFragment)
                }
            }
        })

        countDownTimer?.get()?.start()
    }

    override fun onDestroyView() {
        binding?.relativeLayout?.removeAllViewsInLayout()
        binding?.unbind()
        binding = null
        countDownTimer?.get()?.cancel()
        super.onDestroyView()
    }

    override fun onDestroy() {
        binding?.relativeLayout?.removeAllViewsInLayout()
        binding?.unbind()
        binding = null
        countDownTimer?.get()?.cancel()
        viewModel = null
        super.onDestroy()
    }
}