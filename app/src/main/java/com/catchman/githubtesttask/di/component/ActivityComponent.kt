package com.catchman.githubtesttask.di.component

import com.catchman.githubtesttask.ui.profile.ProfileActivity
import com.catchman.githubtesttask.ui.userList.UserListActivity
import com.catchman.githubtesttask.di.PerActivity
import com.catchman.githubtesttask.di.modul.ActivityModule
import dagger.Component




@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun inject(userListActivity: UserListActivity)

    fun inject(profileActivity: ProfileActivity)


}