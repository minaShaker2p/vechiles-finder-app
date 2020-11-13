package com.rezkalla.vehiclesfinder.di

import com.rezkalla.vehiclesfinder.ui.ListFragment
import com.rezkalla.vehiclesfinder.ui.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector()
    internal abstract fun provideMapFragment(): MapFragment

    @ContributesAndroidInjector()
    internal abstract fun provideListFragment(): ListFragment
}