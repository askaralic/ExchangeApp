package com.exchange.hamilton.di.modules


import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.exchange.hamilton.presentation.fragments.*

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeCurrencyListFragment(): CurrencyListFragment

    @ContributesAndroidInjector
    abstract fun contributeConvertFragment(): ConvertFragment

    @ContributesAndroidInjector
    abstract fun contributeApprovalFragment(): ApprovalFragment

    @ContributesAndroidInjector
    abstract fun contributeConfirmFragment(): ConfirmFragment

    @ContributesAndroidInjector
    abstract fun contributeConfigurationFragment(): ConfigurationFragment

    @ContributesAndroidInjector
    abstract fun contributeCurrencyConfigurationFragment(): CurrencyConfigurationFragment



}
