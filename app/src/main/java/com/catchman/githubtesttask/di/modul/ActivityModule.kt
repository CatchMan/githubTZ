package com.catchman.githubtesttask.di.modul

import android.support.v7.app.AppCompatActivity
import com.catchman.githubtesttask.di.ActivityContext
import com.catchman.githubtesttask.di.PerActivity
import com.catchman.githubtesttask.ui.profile.ProfileContract
import com.catchman.githubtesttask.ui.profile.ProfilePresenter
import com.catchman.githubtesttask.ui.userList.UserListContract
import com.catchman.githubtesttask.ui.userList.UserListPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext() = activity

    @Provides
    fun provideActivity() = activity

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    @PerActivity
    fun provideProfilePresenter (profilePresenter: ProfilePresenter<ProfileContract.View>) :
            ProfileContract.Presenter<ProfileContract.View> = profilePresenter

    @Provides
    @PerActivity
    fun provideUserListPresenter (userListPresenter: UserListPresenter<UserListContract.View>) :
            UserListContract.Presenter<UserListContract.View> = userListPresenter

}