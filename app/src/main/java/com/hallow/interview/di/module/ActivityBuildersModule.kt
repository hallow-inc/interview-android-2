package com.hallow.interview.di.module

import com.hallow.interview.di.main.MainFragmentBuildersModule
import com.hallow.interview.di.main.MainModule
import com.hallow.interview.di.main.MainViewModelsModule
import com.hallow.interview.scenes.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class])
    abstract fun contributeNavActivity(): MainActivity
}
