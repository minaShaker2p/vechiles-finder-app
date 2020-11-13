# Project Description
  it is very simple feature for getting location information for vehicles  from **RestAPI** and show this info in Map and List .


## Setup
first you need to configure add your **google_maps_key** in your google_maps_api.xml in your values folder and also add your SHA1 .

## Project Structure
this application divided to 4 Modules based on **Clean Architecture** layers inspired by uncle bob:
* app : that contain UI and presentation (view model) and Dependency injection configuration (Dagger 2) and Network Client (Retrofit).
* domain :that contain Use Cases (Interactors) for our project , Domain Model and Repository interface which should be implemented by Data layer .
* data : that contain the implementation for our repositories and work as Single source of truth for our Data **(SSOT)** and depend on abstraction for RemoteData and LocalData .
* remote : that contain the implementation for out RemoteData and also depend on API Service interface which provide by our app Module.

## Tech Stack
* Kotlin .
* Clean Architecture .
* Android Architecture Component (AAC) liveData ,viewModel , Navigation component.
* MVVM .
* Dependency Injection (Dagger2) .
* Reactive Paradigm (RxJava2 and RxKotlin2) .
* Retrofit 2 and OkHttp for Networking .
* Unit Test using Junit 4 and Mockito.
* google Maps.

## Overview
project code architecture follow clean architecture inspired by onion architecture by Uncle bob .


![Clean Architecture](https://koenig-media.raywenderlich.com/uploads/2019/06/Clean-Architecture-graph.png)




## References
[Raywenderlich](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started)

[Android App, Clean Architecture + mvvm + Modularization](https://medium.com/@ruddns8213/android-app-clean-architecture-mvvm-modularization-sample-acced8ed3970)





