package com.hallow.interview.di.main

import com.hallow.interview.api.MainApi
import com.hallow.interview.api.repositories.ProfileRepository
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideProfileRepository(api: MainApi) = ProfileRepository(api)
}
