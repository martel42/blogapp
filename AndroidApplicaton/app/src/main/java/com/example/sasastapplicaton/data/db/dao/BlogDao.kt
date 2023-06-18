package com.example.sasastapplicaton.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sasastapplicaton.data.db.entity.Blog

@Dao
interface BlogDao {
    @Query("select * from blog")
    fun getAll(): LiveData<List<Blog>>
//    @Query("SELECT * FROM blog WHERE id Like :blogId")
//    fun loadById(blogId: Long): Blog
    @Insert
      fun insert(blog: Blog)

    @Insert
    fun insertAll(blog: List<Blog>)
    @Update
      fun update(blog: Blog)
    @Delete
      fun delete(blog: Blog)
}