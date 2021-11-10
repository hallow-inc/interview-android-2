package com.hallow.interview.di.main

import com.hallow.interview.scenes.StreakFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeStreakFragment(): StreakFragment
}
