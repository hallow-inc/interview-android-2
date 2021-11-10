package com.hallow.interview.di

import android.app.Application
import com.hallow.interview.di.module.ActivityBuildersModule
import com.hallow.interview.di.module.NetworkModule
import com.hallow.interview.di.module.ViewModelFactoryModule
import com.hallow.interview.scenes.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
