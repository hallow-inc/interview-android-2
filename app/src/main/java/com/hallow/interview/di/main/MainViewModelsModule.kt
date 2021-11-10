package com.hallow.interview.di.main

import androidx.lifecycle.ViewModel
import com.hallow.interview.di.module.ViewModelKey
import com.hallow.interview.scenes.StreakViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(StreakViewModel::class)
    internal abstract fun bindStreakViewModel(viewModel: StreakViewModel): ViewModel
}
