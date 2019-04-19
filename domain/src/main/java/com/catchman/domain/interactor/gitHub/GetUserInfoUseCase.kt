package com.catchman.domain.interactor.gitHub

import com.catchman.domain.executor.PostExecutionThread
import com.catchman.domain.executor.ThreadExecutor
import com.catchman.domain.gateway.GitHubGateway
import com.catchman.domain.model.UserInfo
import com.example.domain.interactor.UseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class GetUserInfoUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    compositeDisposable: CompositeDisposable,
    private val sferaGateway: GitHubGateway
) : UseCase<UserInfo, GetUserInfoUseCase.Params>(threadExecutor, postExecutionThread, compositeDisposable) {

    override fun buildUseCaseObservable(params: Params): Single<UserInfo> = sferaGateway.getUserInfo(params.login)

    class Params(
        val login: String
    )
}