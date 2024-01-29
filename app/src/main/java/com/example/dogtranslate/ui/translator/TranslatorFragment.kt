package com.example.dogtranslate.ui.translator

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import android.os.SystemClock
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.dogtranslate.R
import com.example.dogtranslate.data.local.model.Dog
import com.example.dogtranslate.databinding.DialogConfirmationBinding
import com.example.dogtranslate.databinding.FragmentTranslatorBinding
import com.example.dogtranslate.ui.base.BaseActivity
import com.example.dogtranslate.ui.base.BaseFragment
import com.example.dogtranslate.util.setOnSingClickListener
import java.io.File
import java.io.IOException

class TranslatorFragment : BaseFragment<FragmentTranslatorBinding, TranslatorViewModel>() {
    private var isHuman = true
    private var bindingDialog: DialogConfirmationBinding? = null
    private var isRecording = false
    private var mediaRecorder: MediaRecorder? = null
    private var fileName: String? = null
    private val mHelpGuidList = ArrayList<Dog>()
    private val listDogToHuman = ArrayList<Dog>()
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permissions ->
            if (permissions) {
                Toast.makeText(requireContext(), "Authorization successful", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "authorization failed", Toast.LENGTH_SHORT).show()
            }
        }

    override fun layoutRes(): Int {
        return R.layout.fragment_translator
    }

    override fun viewModelClass(): Class<TranslatorViewModel> {
        return TranslatorViewModel::class.java
    }

    override fun initView() {
        viewModel?.isLoading?.observe(this){
            if (it){
                (activity as? BaseActivity<*, *>)?.showLoading()
            }else {
                (activity as? BaseActivity<*, *>)?.hiddenLoading()
            }
        }
        setHelpGuidList()
        setListDogToHuman()
        setUpListener()

        val root = context?.getExternalFilesDir(null)
        val directory = File(root, "dogTranslate/Audios")
        fileName = "$directory ${System.currentTimeMillis()}.mp3"
        binding?.ivHuman?.setOnSingClickListener {
            val m = MediaPlayer()
            m.setDataSource(fileName)
            m.prepare()
            m.start()
        }
    }

    private fun setUpListener() {
        binding?.ivSwitchHuman?.setOnSingClickListener {
            binding?.ivDog?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_human
                )
            )
            binding?.ivHuman?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_dog
                )
            )
            binding?.ivSwitchDog?.visibility = View.VISIBLE
            binding?.ivSwitchHuman?.visibility = View.GONE
            isHuman = false
        }

        binding?.ivSwitchDog?.setOnSingClickListener {
            binding?.ivDog?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_dog
                )
            )
            binding?.ivHuman?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_human
                )
            )
            binding?.ivSwitchDog?.visibility = View.GONE
            binding?.ivSwitchHuman?.visibility = View.VISIBLE
            isHuman = true
        }

        binding?.ivVoice?.setOnSingClickListener {
            val permissionRecordAudio = Manifest.permission.RECORD_AUDIO
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    permissionRecordAudio
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                if (!isRecording) {
                    startRecording()
                } else {
                    stopRecording()
                }
            } else {
                requestPermissionLauncher.launch(permissionRecordAudio)
            }
        }
    }

    private fun startRecording() {
        Glide.with(requireContext()).load(R.drawable.img_wave_form).into(binding?.ivWave!!)
        binding?.ivVoice?.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_play_record
            )
        )
        binding?.ivLineGrab?.visibility = View.GONE
        binding?.ivWave?.visibility = View.VISIBLE
        binding?.chronometer?.base = SystemClock.elapsedRealtime()
        binding?.chronometer?.start()

        val root = context?.getExternalFilesDir(null)
        val directory = File(root, "dogTranslate/Audios")

        if (!directory.exists()) {
            directory.mkdirs()
        }

        fileName = "$directory ${System.currentTimeMillis()}.mp3"

        mediaRecorder = MediaRecorder()
        mediaRecorder?.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(fileName)
            try {
                mediaRecorder?.prepare()
                mediaRecorder?.start()
                isRecording = true
            } catch (e: IOException) {
                e.printStackTrace()
                isRecording = false
            }
        }
        isRecording = true
    }

    private fun stopRecording() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        bindingDialog = DialogConfirmationBinding.inflate(layoutInflater)
        builder.setView(bindingDialog!!.root)
        val alertDialog = builder.create()

        binding?.ivVoice?.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_record
            )
        )
        binding?.ivLineGrab?.visibility = View.VISIBLE
        binding?.ivWave?.visibility = View.GONE
        binding?.chronometer?.stop()

        mediaRecorder?.apply {
            stop()
            reset()
            release()
        }
        isRecording = false

        if (isHuman) {
            val randomDog = mHelpGuidList.random()
            bindingDialog?.ivDogTranslate?.setImageResource(randomDog.srcDog!!)
            bindingDialog?.tvTitle?.text = "${randomDog.title}"

            val mediaPlayer =
                MediaPlayer.create(requireContext(), randomDog.idSound) // Use create() method

            if (alertDialog.window != null) {
                alertDialog?.window?.setBackgroundDrawable(ColorDrawable(0))
            }
            alertDialog.show()

            mediaPlayer.isLooping = false
            mediaPlayer.start()

            mediaPlayer.setOnCompletionListener {
                alertDialog.dismiss()
            }

        } else {
            val mediaPlayer = MediaPlayer()
            val randomDog = listDogToHuman.random()
            bindingDialog?.ivDogTranslate?.setImageResource(randomDog.srcDog!!)
            bindingDialog?.tvTitle?.text = "${randomDog.title}"

            try {
                mediaPlayer.setDataSource(fileName)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                mediaPlayer.setOnCompletionListener { mp ->
                    mp.release()
                }
            }
            if (alertDialog.window != null) {
                alertDialog?.window?.setBackgroundDrawable(ColorDrawable(0))
            }
            alertDialog.show()
        }

        bindingDialog?.btnClose?.setOnSingClickListener {
            alertDialog.dismiss()
        }
    }

    private fun setHelpGuidList() {
        val dogHi = Dog().apply {
            title = "Hi"
            srcDog = R.drawable.ic_sounds_hi
            idSound = R.raw.dog_hi
        }
        mHelpGuidList.add(dogHi)

        val dogWonder = Dog().apply {
            title = "Wonder"
            srcDog = R.drawable.ic_sounds_wonder
            idSound = R.raw.dog_wonder
        }
        mHelpGuidList.add(dogWonder)

        val dogHappy = Dog().apply {
            title = "Happy"
            srcDog = R.drawable.ic_sounds_happy
            idSound = R.raw.dog_happy
        }
        mHelpGuidList.add(dogHappy)

        val dogLove = Dog().apply {
            title = "Love"
            srcDog = R.drawable.ic_sounds_love
            idSound = R.raw.dog_love
        }
        mHelpGuidList.add(dogLove)

        val dogHappyWalk = Dog().apply {
            title = "Happy Walk"
            srcDog = R.drawable.ic_sounds_happy_walk
            idSound = R.raw.dog_happy_walk
        }
        mHelpGuidList.add(dogHappyWalk)

        val dogDance = Dog().apply {
            title = "Dance"
            srcDog = R.drawable.ic_sounds_dance
            idSound = R.raw.dog_dance
        }
        mHelpGuidList.add(dogDance)

        val dogAgree = Dog().apply {
            title = "Agree"
            srcDog = R.drawable.ic_sound_agree
            idSound = R.raw.dog_agree
        }
        mHelpGuidList.add(dogAgree)

        val dogHandclap = Dog().apply {
            title = "Handclap"
            srcDog = R.drawable.ic_sounds_handclap
            idSound = R.raw.dog_handclap
        }
        mHelpGuidList.add(dogHandclap)

        val dogHiFence = Dog().apply {
            title = "Hi fence"
            srcDog = R.drawable.ic_sounds_hi_fence
            idSound = R.raw.dog_hi_fence
        }
        mHelpGuidList.add(dogHiFence)

        val dogYes = Dog().apply {
            title = "Yes"
            srcDog = R.drawable.ic_sounds_yes
            idSound = R.raw.sound_1
        }
        mHelpGuidList.add(dogYes)

        val dogNo = Dog().apply {
            title = "No"
            srcDog = R.drawable.ic_sounds_no
            idSound = R.raw.dog_no
        }
        mHelpGuidList.add(dogNo)

        val dogWow = Dog().apply {
            title = "Wow"
            srcDog = R.drawable.ic_sounds_wow
            idSound = R.raw.dog_wow
        }
        mHelpGuidList.add(dogWow)

        val dogSad = Dog().apply {
            title = "Sad"
            srcDog = R.drawable.ic_sounds_sad
            idSound = R.raw.dog_sad
        }
        mHelpGuidList.add(dogSad)

        val dogCry = Dog().apply {
            title = "Cry"
            srcDog = R.drawable.ic_sounds_cry
            idSound = R.raw.dog_cry
        }
        mHelpGuidList.add(dogCry)

        val dogCryLying = Dog().apply {
            title = "Cry Lying"
            srcDog = R.drawable.ic_sounds_cry_lying
            idSound = R.raw.dog_crying
        }
        mHelpGuidList.add(dogCryLying)

        val dogPet = Dog().apply {
            title = "Pet"
            srcDog = R.drawable.ic_sounds_pet
            idSound = R.raw.dog_pet
        }
        mHelpGuidList.add(dogPet)

        val dogBegging = Dog().apply {
            title = "Begging"
            srcDog = R.drawable.ic_sounds_begging
            idSound = R.raw.dog_begging
        }
        mHelpGuidList.add(dogBegging)

        val dogWantSleep = Dog().apply {
            title = "Want Sleep"
            srcDog = R.drawable.ic_sounds_want_sleep
            idSound = R.raw.dog_want_sleep
        }
        mHelpGuidList.add(dogWantSleep)

        val dogYeah = Dog().apply {
            title = "Yeah"
            srcDog = R.drawable.ic_sounds_yeah
            idSound = R.raw.dog_yeah
        }
        mHelpGuidList.add(dogYeah)

        val dogLie = Dog().apply {
            title = "Lie"
            srcDog = R.drawable.ic_sounds_lie
            idSound = R.raw.dog_lie
        }
        mHelpGuidList.add(dogLie)

        val dogStartle = Dog().apply {
            title = "Startle"
            srcDog = R.drawable.ic_sounds_startle
            idSound = R.raw.dog_startle
        }
        mHelpGuidList.add(dogStartle)

        val dogSick = Dog().apply {
            title = "Sick"
            srcDog = R.drawable.ic_sounds_sick
            idSound = R.raw.dog_sick
        }
        mHelpGuidList.add(dogSick)

        val dogExhausted = Dog().apply {
            title = "Exhausted"
            srcDog = R.drawable.ic_sounds_exhausted
            idSound = R.raw.dog_exhausted
        }
        mHelpGuidList.add(dogExhausted)

        val dogScared = Dog().apply {
            title = "Scared"
            srcDog = R.drawable.ic_sounds_scared
            idSound = R.raw.dog_scared
        }
        mHelpGuidList.add(dogScared)

        val dogAngry = Dog().apply {
            title = "Angry"
            srcDog = R.drawable.ic_sounds_angry
            idSound = R.raw.dog_angry
        }
        mHelpGuidList.add(dogAngry)

        val dogSuperAngry = Dog().apply {
            title = "Super Angry"
            srcDog = R.drawable.ic_sounds_super_angry
            idSound = R.raw.dog_super_angry
        }
        mHelpGuidList.add(dogSuperAngry)
    }

    private fun setListDogToHuman() {
        val dogHi = Dog().apply {
            title = getString(R.string.label_hi)
            srcDog = R.drawable.ic_sounds_hi
        }
        listDogToHuman.add(dogHi)

        val dogWonder = Dog().apply {
            title = getString(R.string.label_doing)
            srcDog = R.drawable.ic_sounds_wonder
        }
        listDogToHuman.add(dogWonder)

        val dogHappy = Dog().apply {
            title = getString(R.string.label_happy)
            srcDog = R.drawable.ic_sounds_happy
        }
        listDogToHuman.add(dogHappy)

        val dogLove = Dog().apply {
            title = getString(R.string.label_love)
            srcDog = R.drawable.ic_sounds_love
        }
        listDogToHuman.add(dogLove)

        val dogHappyWalk = Dog().apply {
            title = getString(R.string.label_happy_walk)
            srcDog = R.drawable.ic_sounds_happy_walk
        }
        listDogToHuman.add(dogHappyWalk)

        val dogDance = Dog().apply {
            title = getString(R.string.label_dance)
            srcDog = R.drawable.ic_sounds_dance
        }
        listDogToHuman.add(dogDance)

        val dogAgree = Dog().apply {
            title = getString(R.string.label_doing)
            srcDog = R.drawable.ic_sound_agree
        }
        listDogToHuman.add(dogAgree)

        val dogHandClap = Dog().apply {
            title = getString(R.string.label_hand_clap)
            srcDog = R.drawable.ic_sounds_handclap
        }
        listDogToHuman.add(dogHandClap)

        val dogHiFence = Dog().apply {
            title = getString(R.string.label_hi_fence)
            srcDog = R.drawable.ic_sounds_hi_fence
        }
        listDogToHuman.add(dogHiFence)

        val dogYes = Dog().apply {
            title = getString(R.string.label_sound_yes)
            srcDog = R.drawable.ic_sounds_yes
        }
        listDogToHuman.add(dogYes)

        val dogNo = Dog().apply {
            title = getString(R.string.label_sound_no)
            srcDog = R.drawable.ic_sounds_no
        }
        listDogToHuman.add(dogNo)

        val dogWow = Dog().apply {
            title = getString(R.string.label_sound_wow)
            srcDog = R.drawable.ic_sounds_wow
        }
        listDogToHuman.add(dogWow)

        val dogSad = Dog().apply {
            title = getString(R.string.label_sound_sad)
            srcDog = R.drawable.ic_sounds_sad
        }
        listDogToHuman.add(dogSad)

        val dogCry = Dog().apply {
            title = getString(R.string.label_sound_cry)
            srcDog = R.drawable.ic_sounds_cry
        }
        listDogToHuman.add(dogCry)

        val dogCryLying = Dog().apply {
            title = getString(R.string.label_sound_cry_lying)
            srcDog = R.drawable.ic_sounds_cry_lying
        }
        listDogToHuman.add(dogCryLying)

        val dogPet = Dog().apply {
            title = getString(R.string.label_sound_pet)
            srcDog = R.drawable.ic_sounds_pet
        }
        listDogToHuman.add(dogPet)

        val dogBegging = Dog().apply {
            title = getString(R.string.label_sound_begging)
            srcDog = R.drawable.ic_sounds_begging
        }
        listDogToHuman.add(dogBegging)

        val dogWantSleep = Dog().apply {
            title = getString(R.string.label_sound_want_sleep)
            srcDog = R.drawable.ic_sounds_want_sleep
        }
        listDogToHuman.add(dogWantSleep)

        val dogYeah = Dog().apply {
            title = getString(R.string.label_sound_yeah)
            srcDog = R.drawable.ic_sounds_yeah
        }
        listDogToHuman.add(dogYeah)

        val dogLie = Dog().apply {
            title = getString(R.string.label_sound_lie)
            srcDog = R.drawable.ic_sounds_lie
        }
        listDogToHuman.add(dogLie)

        val dogStartle = Dog().apply {
            title = getString(R.string.label_sound_startle)
            srcDog = R.drawable.ic_sounds_startle
        }
        listDogToHuman.add(dogStartle)

        val dogSick = Dog().apply {
            title = getString(R.string.label_doing)
            srcDog = R.drawable.ic_sounds_sick
        }
        listDogToHuman.add(dogSick)

        val dogExhausted = Dog().apply {
            title = getString(R.string.label_sound_exhausted)
            srcDog = R.drawable.ic_sounds_exhausted
        }
        listDogToHuman.add(dogExhausted)

        val dogScared = Dog().apply {
            title = getString(R.string.label_scare)
            srcDog = R.drawable.ic_sounds_scared
        }
        listDogToHuman.add(dogScared)

        val dogAngry = Dog().apply {
            title = getString(R.string.label_argry)
            srcDog = R.drawable.ic_sounds_angry
        }
        listDogToHuman.add(dogAngry)

        val dogSuperAngry = Dog().apply {
            title = getString(R.string.label_super_argry)
            srcDog = R.drawable.ic_sounds_super_angry
        }
        listDogToHuman.add(dogSuperAngry)
    }


    override fun onDestroyView() {
        binding?.relativeLayout?.removeAllViews()
        bindingDialog = null
        binding = null
        super.onDestroyView()
        mediaRecorder?.stop()
        mHelpGuidList.clear()
        listDogToHuman.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
    }
}
