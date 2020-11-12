package com.rezkalla.vehiclesfinder.di

import android.app.Application
import com.rezkalla.vehiclesfinder.application.VehiclesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DomainModule::class,
        DataModule::class,
        RemoteModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<VehiclesApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: VehiclesApplication)
}