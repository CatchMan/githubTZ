package com.catchman.data.github.storage

import com.example.data.AppDatabase
import io.reactivex.Single
import javax.inject.Inject



class GitHubStorageImpl @Inject constructor(database: AppDatabase) : GitHubStorage {

    private val todoDao = database.getTodoDao()


    override fun putUsers(users: List<StorageUser>): Single<String> =
        Single.fromCallable {
            todoDao.updateData(users)
            "OK"
        }

    override fun getAllUsers(): Single<List<StorageUser>> = todoDao.getAll()

}