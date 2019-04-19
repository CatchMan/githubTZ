package com.catchman.data.github.storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.catchman.data.github.storage.StorageUser
import io.reactivex.Single




//TODO implement BaseDao https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1

@Dao
interface GitHubDao {

    @Transaction
    fun updateData(users: List<StorageUser>){
        deleteAll()
        insertAll(users)
    }
    @Query("SELECT * FROM user")
    fun getAll(): Single<List<StorageUser>>

    @Insert(onConflict = REPLACE)
    fun insertAll(users: List<StorageUser>)

    @Query("DELETE from user")
    fun deleteAll()
}
