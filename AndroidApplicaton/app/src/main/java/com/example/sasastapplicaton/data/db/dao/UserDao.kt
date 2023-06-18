package com.example.sasastapplicaton.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sasastapplicaton.data.db.entity.User

@Dao
interface UserDao {
   @Query("select * from user")
    fun getAll(): List<User>
//   @Query("SELECT * FROM user WHERE uid Like :userId")
//   fun loadById(userId: Long): User
   @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userList: List<User>)
   @Update
    fun update(user: User)
   @Delete
    fun delete(user: User)
}