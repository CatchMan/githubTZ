package com.catchman.data.github.storage

import io.reactivex.Single




interface GitHubStorage {


    fun putUsers(countries: List<StorageUser>): Single<String>
    fun getAllUsers(): Single<List<StorageUser>>


}