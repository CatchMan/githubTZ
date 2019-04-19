package com.catchman.data.github.service

import io.reactivex.Single


/**
 * Created by ujujzk on 21.06.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

interface GitHubService {

    fun getCommonUsers(since: Long): Single<List<CommonUser>>

    fun getUserInfo(login: String): Single<UserInfo>

}