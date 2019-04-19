package com.catchman.data.github.service

import com.catchman.data.github.service.*
import io.reactivex.Single
import javax.inject.Inject



class GitHubServiceImpl @Inject constructor(val gitHubApi: GitHubApi): GitHubService {

    override fun getCommonUsers(since: Long): Single<List<CommonUser>> = this.gitHubApi.getCommonUsers(since)

    override fun getUserInfo(login: String): Single<UserInfo> = this.gitHubApi.getUserInfo(login)
}