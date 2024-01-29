package com.example.dogtranslate.ui.showsounddog

import android.media.MediaPlayer
import android.os.CountDownTimer
import android.os.Handler
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.dogtranslate.R
import com.example.dogtranslate.data.local.model.Dog
import com.example.dogtranslate.databinding.FragmentShowSoundDogBinding
import com.example.dogtranslate.ui.base.BaseFragment
import java.text.SimpleDateFormat

class ShowSoundDogFragment : BaseFragment<FragmentShowSoundDogBinding, ShowSoundDogViewModel>() {
    private var mediaPlayer: MediaPlayer? = null
    private var pausePosition: Int = 0
    private var isPlaying = false
    private var isSoundCompleted = false
    private var isLoop: Boolean = false
    private var mediaDuration: Int = 0

    override fun layoutRes(): Int {
        return R.layout.fragment_show_sound_dog
    }

    override fun viewModelClass(): Class<ShowSoundDogViewModel> {
        return ShowSoundDogViewModel::class.java
    }

    override fun initView() {
        val dog = arguments?.getParcelable("dog") as? Dog

        if (dog != null) {
            binding?.abShowSoundDog?.setTitle(dog.title!!)
            binding?.imgView?.background = ContextCompat.getDrawable(requireContext(), dog.srcDog!!)
        }

        binding?.play?.setImageResource(R.drawable.ic_play_sound)

        binding?.play?.setOnClickListener {
            togglePlay()
            setTimeTotal()
            updateTimeSound()
        }

        binding?.seekbar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer?.seekTo(binding?.seekbar?.progress!!)
            }
        })

        binding?.switchCompat?.setOnCheckedChangeListener { _, isChecked ->
            isLoop = isChecked
        }

        binding?.abShowSoundDog?.setOnClickBack {
            findNavController().popBackStack()
        }
    }

    private fun togglePlay() {
        if (isPlaying) {
            binding?.play?.setImageResource(R.drawable.ic_play_sound)
            pausePlayback()
        } else {
            binding?.play?.setImageResource(R.drawable.ic_pause_sound)
            startPlayback()
        }
    }

    private fun startPlayback() {
        val dog = arguments?.getParcelable("dog") as? Dog
        if (dog != null) {
            if (isSoundCompleted) {
                mediaPlayer?.seekTo(0)
                isSoundCompleted = false
            } else {
                mediaPlayer?.seekTo(pausePosition)
            }
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(requireContext(), dog.idSound)
                mediaPlayer?.seekTo(pausePosition)
                mediaPlayer?.start()
                mediaDuration = mediaPlayer?.duration ?: 0
                binding?.seekbar?.max = mediaDuration

                isPlaying = true
                binding?.play?.setImageResource(R.drawable.ic_pause_sound)

                mediaPlayer?.setOnCompletionListener {
                    isSoundCompleted = true
                }
            } else {
                mediaPlayer?.start()
                isPlaying = true
                binding?.play?.setImageResource(R.drawable.ic_pause_sound)
            }
        }
    }

    private fun pausePlayback() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            pausePosition = mediaPlayer?.currentPosition!!
            isPlaying = false
            binding?.play?.setImageResource(R.drawable.ic_play_sound)
        }
    }

    private fun resetPlayback() {
        mediaPlayer?.seekTo(0)
        isPlaying = false
        binding?.seekbar?.progress = 0
        binding?.play?.setImageResource(R.drawable.ic_play_sound)
    }

    private fun setTimeTotal() {
        val simpleDateFormat = SimpleDateFormat("mm:ss")
        binding?.tvEndTime?.text = simpleDateFormat.format(mediaPlayer?.duration)
        binding?.seekbar?.max = mediaPlayer?.duration!!
    }

    private fun updateTimeSound() {
        val handler = Handler()

        handler.post(object : Runnable {
            override fun run() {
                val simpleDateFormat = SimpleDateFormat("mm:ss")
                binding?.tvStartTime?.text = simpleDateFormat.format(mediaPlayer?.currentPosition)
                binding?.seekbar?.progress = mediaPlayer?.currentPosition!!

                if (mediaPlayer?.isPlaying == false && isSoundCompleted) {
                    resetPlayback()
                    return
                }

                handler.postDelayed(this, 100)
            }
        })
    }

    private fun stopPlayback() {
        if (mediaPlayer != null) {
            mediaPlayer?.reset()
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }

        pausePosition = 0
        isPlaying = false
        binding?.play?.setImageResource(R.drawable.ic_play_sound)

    }

    override fun onDestroyView() {
        binding?.relativeLayout?.removeAllViews()
        binding = null
        super.onDestroyView()
        stopPlayback()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
    }
}