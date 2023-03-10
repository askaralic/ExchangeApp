package com.exchange.hamilton.di.modules


import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.exchange.hamilton.presentation.MainActivity

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentBuildersModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
