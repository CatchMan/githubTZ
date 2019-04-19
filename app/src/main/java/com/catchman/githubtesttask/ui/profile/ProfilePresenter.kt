package com.catchman.githubtesttask.ui.profile

import com.catchman.domain.interactor.gitHub.GetUserInfoUseCase
import com.catchman.domain.model.UserInfo
import com.catchman.githubtesttask.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject


class ProfilePresenter<V : ProfileContract.View> @Inject constructor(
    val getUserInfoUseCase: GetUserInfoUseCase,
    compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(compositeDisposable), ProfileContract.Presenter<V> {

    override fun getProfile(login: String) {
        view?.showProgress(true)
        getUserInfoUseCase.execute(object : DisposableSingleObserver<UserInfo>(){
            override fun onSuccess(t: UserInfo) {
                view?.showProgress(false)
                view?.getProfileSuccess(t)
            }

            override fun onError(e: Throwable) {
                view?.showProgress(false)
                view?.getProfileFail()
            }
        },GetUserInfoUseCase.Params(login))
    }
}