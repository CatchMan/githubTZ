package com.catchman.githubtesttask.di.component

import com.catchman.githubtesttask.di.PerFragment
import com.catchman.githubtesttask.di.modul.ActivityModule
import com.catchman.githubtesttask.di.modul.FragmentModule
import dagger.Component




@PerFragment
@Component(dependencies = [(ApplicationComponent::class)], modules = [(FragmentModule::class),(ActivityModule::class)])
interface FragmentComponent {

}