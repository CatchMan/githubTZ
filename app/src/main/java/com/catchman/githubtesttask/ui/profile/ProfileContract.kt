package com.catchman.githubtesttask.ui.profile

import com.catchman.domain.model.CommonUser
import com.catchman.domain.model.UserInfo
import com.catchman.githubtesttask.ui.base.IPresenter
import com.catchman.githubtesttask.ui.base.IView

interface ProfileContract {

    interface View : IView {

        fun getProfileSuccess(userInfo: UserInfo)

        fun getProfileFail()



    }

    interface Presenter<V : View> : IPresenter<V> {

        fun getProfile(login: String)


    }

}