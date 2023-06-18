package com.example.sasastapplicaton.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sasastapplicaton.data.db.entity.Subs

@Dao
interface SubsDao {
    @Query("select * from subs")
      fun getAll(): LiveData<List<Subs>>
//    @Query("SELECT * FROM subs WHERE id Like :subsId")
//    fun loadById(subsId: Long): Subs
    @Insert
      fun insert(subs: Subs)

    @Insert
    fun insertAll(subs: List<Subs>)
    @Update
      fun update(subs: Subs)
    @Delete
      fun delete(subs: Subs)

      @Query("Delete from subs where user_id = :uID AND user_subs_id = :bID")
      fun deletePlus(uID: Long, bID: Long)
}