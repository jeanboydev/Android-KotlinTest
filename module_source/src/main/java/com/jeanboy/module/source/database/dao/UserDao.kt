package com.jeanboy.module.source.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jeanboy.module.source.database.entity.UserEntity

/**
 *
 * @author caojianbo
 * @since 2020/1/14 15:07
 */
@Dao
interface UserDao {

    @Query("select * from user where id=:userId")
    fun getById(userId: Long): LiveData<UserEntity>

    @Query("select * from user where username=:username")
    fun getByUsername(username: String): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: UserEntity)

    @Delete
    fun delete(entity: UserEntity)
}