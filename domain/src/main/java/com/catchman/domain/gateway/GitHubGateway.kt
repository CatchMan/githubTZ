package com.catchman.domain.gateway

import com.catchman.domain.model.CommonUser
import com.catchman.domain.model.UserInfo
import io.reactivex.Single



interface GitHubGateway {

    fun getCommonUsers(since: Long): Single<String>

    fun getUserInfo(login: String): Single<UserInfo>

    fun retrieveCommonUsers(): Single<List<CommonUser>>

}