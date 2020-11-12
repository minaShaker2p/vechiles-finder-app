package com.rezkalla.vehiclesfinder.di


import com.rezkalla.vehiclesfinder.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity
}