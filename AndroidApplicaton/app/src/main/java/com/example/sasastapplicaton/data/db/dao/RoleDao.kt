package com.example.sasastapplicaton.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sasastapplicaton.data.db.entity.Role

@Dao
interface RoleDao {
    @Query("select * from role")
     fun getAll(): LiveData<List<Role>>
//    @Query("SELECT * FROM role WHERE id Like :roleId")
//    fun loadById(roleId: Long): Role
    @Insert
     fun insert(role: Role)
    @Update
     fun update(role: Role)
    @Delete
     fun delete(role: Role)
}