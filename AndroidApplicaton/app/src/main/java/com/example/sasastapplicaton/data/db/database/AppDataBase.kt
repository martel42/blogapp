package com.example.sasastapplicaton.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sasastapplicaton.data.db.dao.BlogDao
import com.example.sasastapplicaton.data.db.dao.BlogSubsDao
import com.example.sasastapplicaton.data.db.dao.PostDao
import com.example.sasastapplicaton.data.db.dao.RoleDao
import com.example.sasastapplicaton.data.db.dao.SubsDao
import com.example.sasastapplicaton.data.db.dao.UserDao
import com.example.sasastapplicaton.data.db.dao.UserSubsDao
import com.example.sasastapplicaton.data.db.entity.Blog
import com.example.sasastapplicaton.data.db.entity.BlogSubs
import com.example.sasastapplicaton.data.db.entity.Post
import com.example.sasastapplicaton.data.db.entity.Role
import com.example.sasastapplicaton.data.db.entity.Subs
import com.example.sasastapplicaton.data.db.entity.User
import com.example.sasastapplicaton.data.db.entity.UserSubs

@Database(entities = [User::class, UserSubs::class, Subs::class, BlogSubs::class,
    Role::class, Post::class, Blog::class], version = 14, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.inMemoryDatabaseBuilder(
                        context,
                        AppDatabase::class.java
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }
    abstract fun userDao(): UserDao
    abstract fun userSubsDao(): UserSubsDao
    abstract fun subsDao(): SubsDao
    abstract fun roleDao(): RoleDao
    abstract fun postDao(): PostDao
    abstract fun BlogDao(): BlogDao
    abstract fun BlogSubsDao(): BlogSubsDao
}