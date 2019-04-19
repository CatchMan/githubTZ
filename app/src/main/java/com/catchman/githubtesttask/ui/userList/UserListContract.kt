package com.catchman.githubtesttask.ui.userList

import com.catchman.domain.model.CommonUser
import com.catchman.githubtesttask.ui.base.IPresenter
import com.catchman.githubtesttask.ui.base.IView

interface UserListContract {

    interface View : IView {

        fun getUsersSuccess(users: List<CommonUser>)

        fun getUsersFail()


    }

    interface Presenter<V : View> : IPresenter<V> {

        fun retrieveUsers()

        fun loadUsers(since: Long)

    }

}