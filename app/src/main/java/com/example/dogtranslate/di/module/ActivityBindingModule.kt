package com.example.dogtranslate.di.module

import com.example.dogtranslate.ui.home.HomeFragment
import com.example.dogtranslate.ui.intro.IntroFragment
import com.example.dogtranslate.ui.language.LanguageFragment
import com.example.dogtranslate.ui.main.MainActivity
import com.example.dogtranslate.ui.showsounddog.ShowSoundDogFragment
import com.example.dogtranslate.ui.showtraining.ShowTrainingFragment
import com.example.dogtranslate.ui.sounds.SoundsFragment
import com.example.dogtranslate.ui.splash.SplashFragment
import com.example.dogtranslate.ui.training.TrainingFragment
import com.example.dogtranslate.ui.translator.TranslatorFragment
import com.example.dogtranslate.ui.whistle.WhistleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun bindIntroFragment(): IntroFragment

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindSoundsFragment(): SoundsFragment

    @ContributesAndroidInjector
    abstract fun bindTranslatorFragment(): TranslatorFragment

    @ContributesAndroidInjector
    abstract fun bindTrainingFragment(): TrainingFragment

    @ContributesAndroidInjector
    abstract fun bindWhistleFragment(): WhistleFragment

    @ContributesAndroidInjector
    abstract fun bindShowSoundDogFragment(): ShowSoundDogFragment

    @ContributesAndroidInjector
    abstract fun bindLanguageFragment(): LanguageFragment

    @ContributesAndroidInjector
    abstract fun bindShowTrainingFragment(): ShowTrainingFragment
}