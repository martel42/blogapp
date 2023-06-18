package com.example.sasastapplicaton.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sasastapplicaton.data.db.entity.UserSubs

@Dao
interface UserSubsDao {
    @Query("select * from userSubs")
      fun getAll(): LiveData<List<UserSubs>>
//    @Query("SELECT * FROM userSubs WHERE id Like :userSubsId")
//    fun loadById(userSubsId: Long): UserSubs
    @Insert
     fun insert(userSubs: UserSubs)

    @Insert
    fun insertAll(userSubs: List<UserSubs>)
    @Update
     fun update(userSubs: UserSubs)
    @Delete
     fun delete(userSubs: UserSubs)
}