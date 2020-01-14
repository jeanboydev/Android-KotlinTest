package com.jeanboy.module.source.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jeanboy.module.source.database.dao.UserDao
import com.jeanboy.module.source.database.entity.UserEntity

/**
 *
 * @author caojianbo
 * @since 2020/1/14 14:49
 */
@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {


    companion object {
        const val NAME: String = "app.db"
        @Volatile
        private var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = build(context)
                }
            }
            return instance!!
        }

        private fun build(context: Context): AppDatabase? {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, NAME)
                .build()
        }
    }

    open fun toMigration() {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE `Fruit` (`id` INTEGER, "
                            + "`name` TEXT, PRIMARY KEY(`id`))"
                )
            }
        }
    }

    abstract fun userDao(): UserDao
}