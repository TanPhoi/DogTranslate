package com.example.dogtranslate.ui.sounds

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.dogtranslate.R
import com.example.dogtranslate.data.local.model.Dog
import com.example.dogtranslate.databinding.FragmentSoundsBinding
import com.example.dogtranslate.ui.base.BaseActivity
import com.example.dogtranslate.ui.base.BaseFragment
import com.example.dogtranslate.ui.sounds.adapter.SoundsAdapter

class SoundsFragment : BaseFragment<FragmentSoundsBinding, SoundsViewModel>() {
    private var adapter: SoundsAdapter? = null
    private var dogList = ArrayList<Dog>()

    override fun layoutRes(): Int {
        return R.layout.fragment_sounds
    }

    override fun viewModelClass(): Class<SoundsViewModel> {
        return SoundsViewModel::class.java
    }

    override fun initView() {
        viewModel?.isLoading?.observe(this){
            if (it){
                (activity as? BaseActivity<*, *>)?.showLoading()
            }else {
                (activity as? BaseActivity<*, *>)?.hiddenLoading()
            }
        }
        setDataDog()
        handleDogAdapter()
    }

    private fun handleDogAdapter() {
        adapter = SoundsAdapter(dogList)
        binding?.rcvDog?.adapter = adapter

        adapter?.itemOnClick = {
            findNavController().navigate(
                R.id.action_homeFragment_to_showSoundDogFragment, bundleOf(
                    Pair("dog", it)
                )
            )
        }
    }

    private fun setDataDog() {
        val dogHi = Dog().apply {
            title = resources.getString(R.string.label_sound_hi)
            srcDog = R.drawable.ic_sounds_hi
            idSound = R.raw.dog_hi
        }
        dogList.add(dogHi)

        val dogWonder = Dog().apply {
            title = resources.getString(R.string.label_sound_wonder)
            srcDog = R.drawable.ic_sounds_wonder
            idSound = R.raw.dog_wonder
        }
        dogList.add(dogWonder)

        val dogHappy = Dog().apply {
            title = resources.getString(R.string.label_sound_happy)
            srcDog = R.drawable.ic_sounds_happy
            idSound = R.raw.dog_happy
        }
        dogList.add(dogHappy)

        val dogLove = Dog().apply {
            title = resources.getString(R.string.label_sound_Love)
            srcDog = R.drawable.ic_sounds_love
            idSound = R.raw.dog_love
        }
        dogList.add(dogLove)

        val dogHappyWalk = Dog().apply {
            title = resources.getString(R.string.label_sound_happy_walk)
            srcDog = R.drawable.ic_sounds_happy_walk
            idSound = R.raw.dog_happy_walk
        }
        dogList.add(dogHappyWalk)

        val dogDance = Dog().apply {
            title = resources.getString(R.string.label_sound_dance)
            srcDog = R.drawable.ic_sounds_dance
            idSound = R.raw.dog_dance
        }
        dogList.add(dogDance)

        val dogAgree = Dog().apply {
            title = resources.getString(R.string.label_sound_agree)
            srcDog = R.drawable.ic_sound_agree
            idSound = R.raw.dog_agree
        }
        dogList.add(dogAgree)

        val dogHandClap = Dog().apply {
            title = resources.getString(R.string.label_sound_hand_clap)
            srcDog = R.drawable.ic_sounds_handclap
            idSound = R.raw.dog_handclap
        }
        dogList.add(dogHandClap)

        val dogHiFence = Dog().apply {
            title = resources.getString(R.string.label_sound_hi_fence)
            srcDog = R.drawable.ic_sounds_hi_fence
            idSound = R.raw.dog_hi_fence
        }
        dogList.add(dogHiFence)

        val dogYes = Dog().apply {
            title = resources.getString(R.string.label_sound_yes)
            srcDog = R.drawable.ic_sounds_yes
            idSound = R.raw.sound_1
        }
        dogList.add(dogYes)

        val dogNo = Dog().apply {
            title = resources.getString(R.string.label_sound_no)
            srcDog = R.drawable.ic_sounds_no
            idSound = R.raw.dog_no
        }
        dogList.add(dogNo)

        val dogWow = Dog().apply {
            title = resources.getString(R.string.label_sound_wow)
            srcDog = R.drawable.ic_sounds_wow
            idSound = R.raw.dog_wow
        }
        dogList.add(dogWow)

        val dogSad = Dog().apply {
            title = resources.getString(R.string.label_sound_sad)
            srcDog = R.drawable.ic_sounds_sad
            idSound = R.raw.dog_sad
        }
        dogList.add(dogSad)

        val dogCry = Dog().apply {
            title = resources.getString(R.string.label_sound_cry)
            srcDog = R.drawable.ic_sounds_cry
            idSound = R.raw.dog_cry
        }
        dogList.add(dogCry)

        val dogCryLying = Dog().apply {
            title = resources.getString(R.string.label_sound_cry_lying)
            srcDog = R.drawable.ic_sounds_cry_lying
            idSound = R.raw.dog_crying
        }
        dogList.add(dogCryLying)

        val dogPet = Dog().apply {
            title = resources.getString(R.string.label_sound_pet)
            srcDog = R.drawable.ic_sounds_pet
            idSound = R.raw.dog_pet
        }
        dogList.add(dogPet)

        val dogBegging = Dog().apply {
            title = resources.getString(R.string.label_sound_begging)
            srcDog = R.drawable.ic_sounds_begging
            idSound = R.raw.dog_begging
        }
        dogList.add(dogBegging)

        val dogWantSleep = Dog().apply {
            title = resources.getString(R.string.label_sound_want_sleep)
            srcDog = R.drawable.ic_sounds_want_sleep
            idSound = R.raw.dog_want_sleep
        }
        dogList.add(dogWantSleep)

        val dogYeah = Dog().apply {
            title = resources.getString(R.string.label_sound_yeah)
            srcDog = R.drawable.ic_sounds_yeah
            idSound = R.raw.dog_yeah
        }
        dogList.add(dogYeah)

        val dogLie = Dog().apply {
            title = resources.getString(R.string.label_sound_lie)
            srcDog = R.drawable.ic_sounds_lie
            idSound = R.raw.dog_lie
        }
        dogList.add(dogLie)

        val dogStartle = Dog().apply {
            title = resources.getString(R.string.label_sound_startle)
            srcDog = R.drawable.ic_sounds_startle
            idSound = R.raw.dog_startle
        }
        dogList.add(dogStartle)

        val dogSick = Dog().apply {
            title = resources.getString(R.string.label_sound_sick)
            srcDog = R.drawable.ic_sounds_sick
            idSound = R.raw.dog_sick
        }
        dogList.add(dogSick)

        val dogExhausted = Dog().apply {
            title = resources.getString(R.string.label_sound_exhausted)
            srcDog = R.drawable.ic_sounds_exhausted
            idSound = R.raw.dog_exhausted
        }
        dogList.add(dogExhausted)

        val dogScared = Dog().apply {
            title = resources.getString(R.string.label_sound_scared)
            srcDog = R.drawable.ic_sounds_scared
            idSound = R.raw.dog_scared
        }
        dogList.add(dogScared)

        val dogAngry = Dog().apply {
            title = resources.getString(R.string.label_sound_angry)
            srcDog = R.drawable.ic_sounds_angry
            idSound = R.raw.dog_angry
        }
        dogList.add(dogAngry)

        val dogSuperAngry = Dog().apply {
            title = resources.getString(R.string.label_sound_super_angry)
            srcDog = R.drawable.ic_sounds_super_angry
            idSound = R.raw.dog_super_angry
        }
        dogList.add(dogSuperAngry)
    }

    override fun onDestroyView() {
        binding?.relativeLayout?.removeAllViews()
        adapter = null
        dogList.clear()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
    }
}