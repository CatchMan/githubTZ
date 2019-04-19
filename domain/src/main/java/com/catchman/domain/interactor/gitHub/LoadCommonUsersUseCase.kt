package com.catchman.domain.interactor.gitHub

import com.catchman.domain.executor.PostExecutionThread
import com.catchman.domain.executor.ThreadExecutor
import com.catchman.domain.gateway.GitHubGateway
import com.catchman.domain.model.CommonUser
import com.example.domain.interactor.UseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class LoadCommonUsersUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    compositeDisposable: CompositeDisposable,
    private val sferaGateway: GitHubGateway
) : UseCase<List<CommonUser>, LoadCommonUsersUseCase.Params>(threadExecutor, postExecutionThread, compositeDisposable) {

    override fun buildUseCaseObservable(params: Params): Single<List<CommonUser>> = sferaGateway.retrieveCommonUsers()

    class Params
}