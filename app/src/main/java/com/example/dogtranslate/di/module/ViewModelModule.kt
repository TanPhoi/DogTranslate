package com.example.dogtranslate.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dogtranslate.di.viewmodel.ViewModelFactory
import com.example.dogtranslate.di.viewmodel.ViewModelKey
import com.example.dogtranslate.ui.home.HomeViewModel
import com.example.dogtranslate.ui.intro.IntroViewModel
import com.example.dogtranslate.ui.language.LanguageViewModel
import com.example.dogtranslate.ui.main.MainViewModel
import com.example.dogtranslate.ui.showsounddog.ShowSoundDogViewModel
import com.example.dogtranslate.ui.showtraining.ShowTrainingViewModel
import com.example.dogtranslate.ui.sounds.SoundsViewModel
import com.example.dogtranslate.ui.splash.SplashViewModel
import com.example.dogtranslate.ui.training.TrainingViewModel
import com.example.dogtranslate.ui.translator.TranslatorViewModel
import com.example.dogtranslate.ui.whistle.WhistleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(IntroViewModel::class)
    internal abstract fun introViewModel(viewModel: IntroViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SoundsViewModel::class)
    internal abstract fun soundsViewModel(viewModel: SoundsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TranslatorViewModel::class)
    internal abstract fun translatorViewModel(viewModel: TranslatorViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TrainingViewModel::class)
    internal abstract fun trainingViewModel(viewModel: TrainingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WhistleViewModel::class)
    internal abstract fun whistleViewModel(viewModel: WhistleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShowSoundDogViewModel::class)
    internal abstract fun showSoundDogViewModel(viewModel: ShowSoundDogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LanguageViewModel::class)
    internal abstract fun languageViewModel(viewModel: LanguageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShowTrainingViewModel::class)
    internal abstract fun showTrainingViewModel(viewModel: ShowTrainingViewModel): ViewModel

}