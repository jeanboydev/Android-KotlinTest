package com.jeanboy.module.source.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author caojianbo
 * @since 2020/1/14 15:01
 */
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "avatar")
    val avatar: String,
    @ColumnInfo(name = "create_at")
    val createAt: Long
)