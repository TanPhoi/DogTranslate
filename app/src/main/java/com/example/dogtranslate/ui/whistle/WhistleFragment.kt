package com.example.dogtranslate.ui.whistle

import android.media.AudioAttributes
import android.media.MediaPlayer
import com.example.dogtranslate.R
import com.example.dogtranslate.databinding.FragmentWhistleBinding
import com.example.dogtranslate.ui.base.BaseActivity
import com.example.dogtranslate.ui.base.BaseFragment

class WhistleFragment : BaseFragment<FragmentWhistleBinding, WhistleViewModel>() {
    private var mediaPlayer: MediaPlayer? = null

    override fun layoutRes(): Int {
        return R.layout.fragment_whistle
    }

    override fun viewModelClass(): Class<WhistleViewModel> {
        return WhistleViewModel::class.java
    }

    override fun initView() {
        viewModel?.isLoading?.observe(this){
            if (it){
                (activity as? BaseActivity<*, *>)?.showLoading()
            }else {
                (activity as? BaseActivity<*, *>)?.hiddenLoading()
            }
        }
        val audioFileResId = R.raw.whistle_sound_1
        mediaPlayer = MediaPlayer()
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()

        mediaPlayer?.setAudioAttributes(audioAttributes)
        val uriPath =
            "android.resource://" + requireActivity().packageName + "/" + audioFileResId
        try {
            mediaPlayer?.setDataSource(requireContext(), android.net.Uri.parse(uriPath))
            mediaPlayer?.prepareAsync()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding?.appCompatImageView?.setOnClickListener {

            binding?.appCompatImageView?.setImageResource(R.drawable.ic_play)
            startSound()
        }

        mediaPlayer?.setOnCompletionListener {
            binding?.appCompatImageView?.setImageResource(R.drawable.ic_pause)
        }
    }

    private fun startSound() {
        if (mediaPlayer != null && !mediaPlayer?.isPlaying!!) {
            mediaPlayer?.start()
        }
    }


    override fun onDestroyView() {
        binding?.relativeLayout?.removeAllViews()
        mediaPlayer?.stop()
        mediaPlayer = null
        binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
    }
}