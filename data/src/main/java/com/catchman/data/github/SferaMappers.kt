package com.catchman.data.github

import com.catchman.data.github.service.UserInfo
import com.catchman.data.github.storage.StorageUser
import com.catchman.domain.model.CommonUser

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function






class CommonUsersFromServiceForStorage : Function<List<com.catchman.data.github.service.CommonUser>, Single<List<StorageUser>>> {
    override fun apply(t: List<com.catchman.data.github.service.CommonUser>): Single<List<StorageUser>> =
        Observable.fromIterable(t)
            .map { StorageUser(it.id, it.login, it.avatarUrl) }
            .toList()
}

class CommonUsersFromStorageToPresentation : Function<StorageUser, CommonUser> {
    override fun apply(t: StorageUser): CommonUser =
        CommonUser(t.id, t.login, t.avatar_url)

}

class UserInfoFromServiceToPresentation  : Function<UserInfo, com.catchman.domain.model.UserInfo> {
    override fun apply(t: UserInfo): com.catchman.domain.model.UserInfo =
        com.catchman.domain.model.UserInfo(t.id, t.publickRepos, t.publicGists, t.followers, t.blog, t.name, t.avatarUrl)

}