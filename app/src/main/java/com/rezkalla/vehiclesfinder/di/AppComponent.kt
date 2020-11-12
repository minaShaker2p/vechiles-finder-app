package com.rezkalla.vehiclesfinder.di

import android.app.Application
import com.rezkalla.vehiclesfinder.application.VehiclesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        NetworkModule::class,
        DomainModule::class,
        DataModule::class,
        RemoteModule::class,
        AppModule::class
    ]
)
interface AppComponent {

    fun inject(app: VehiclesApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}