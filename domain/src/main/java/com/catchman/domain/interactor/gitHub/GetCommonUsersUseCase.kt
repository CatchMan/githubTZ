package com.catchman.domain.interactor.gitHub

import com.catchman.domain.executor.PostExecutionThread
import com.catchman.domain.executor.ThreadExecutor
import com.catchman.domain.gateway.GitHubGateway
import com.example.domain.interactor.UseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class GetCommonUsersUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    compositeDisposable: CompositeDisposable,
    private val sferaGateway: GitHubGateway
) : UseCase<String, GetCommonUsersUseCase.Params>(threadExecutor, postExecutionThread, compositeDisposable) {

    override fun buildUseCaseObservable(params: Params): Single<String> = sferaGateway.getCommonUsers(params.since)

    class Params(
        val since: Long
    )
}