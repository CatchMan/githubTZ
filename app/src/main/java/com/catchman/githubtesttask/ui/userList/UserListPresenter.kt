package com.catchman.githubtesttask.ui.userList

import com.catchman.domain.interactor.gitHub.GetCommonUsersUseCase
import com.catchman.domain.interactor.gitHub.LoadCommonUsersUseCase
import com.catchman.domain.model.CommonUser
import com.catchman.githubtesttask.ui.base.BasePresenter
import com.catchman.githubtesttask.util.logi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject
import kotlin.math.sin


class UserListPresenter<V : UserListContract.View> @Inject constructor(
    val getCommonUsersUseCase: GetCommonUsersUseCase,
    val loadCommonUsersUseCase: LoadCommonUsersUseCase,
    compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(compositeDisposable), UserListContract.Presenter<V> {

    override fun retrieveUsers() {

        loadCommonUsersUseCase.execute(object : DisposableSingleObserver<List<CommonUser>>(){
            override fun onSuccess(t: List<CommonUser>) {
                view?.showProgress(false)
                view?.getUsersSuccess(t)
            }

            override fun onError(e: Throwable) {
                view?.showProgress(false)
                view?.getUsersFail()
            }
        }, LoadCommonUsersUseCase.Params())
    }

    override fun loadUsers(since: Long) {
        view?.showProgress(true)
        getCommonUsersUseCase.execute(object : DisposableSingleObserver<String>(){
            override fun onSuccess(t: String) {
                t.logi()
                retrieveUsers()
            }

            override fun onError(e: Throwable) {
                retrieveUsers()
            }
        }, GetCommonUsersUseCase.Params(since))
    }
}