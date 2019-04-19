package com.catchman.data.github

import com.catchman.data.DataLogger
import com.catchman.data.github.service.GitHubService
import com.catchman.domain.gateway.GitHubGateway
import com.catchman.data.github.storage.GitHubStorage
import com.catchman.domain.model.CommonUser
import com.catchman.domain.model.UserInfo
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import javax.inject.Inject



class GitHubGatewayImpl @Inject constructor(val service: GitHubService, val storage: GitHubStorage, val logger: DataLogger) :
    GitHubGateway {




    override fun getCommonUsers(since: Long): Single<String> =
        service.getCommonUsers(since)
            .flatMap {
                    users ->
                Single.just(users)
                    .flatMap(CommonUsersFromServiceForStorage())
                    .flatMap { storage.putUsers(it) }
                    .subscribeOn(Schedulers.newThread())
            }

    override fun retrieveCommonUsers(): Single<List<CommonUser>> =
        storage.getAllUsers()
            .flatMapObservable { f -> Observable.fromIterable(f) }
            .map(CommonUsersFromStorageToPresentation())
            .toList()

    override fun getUserInfo(login: String): Single<UserInfo> =
            service.getUserInfo(login)
                .map(UserInfoFromServiceToPresentation())

}