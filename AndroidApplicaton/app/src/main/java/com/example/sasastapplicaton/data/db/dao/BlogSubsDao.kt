package com.example.sasastapplicaton.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sasastapplicaton.data.db.entity.Blog
import com.example.sasastapplicaton.data.db.entity.BlogSubs

@Dao
interface BlogSubsDao {
    @Query("select * from blogsubs")
    fun getAll(): LiveData<List<BlogSubs>>
    //    @Query("SELECT * FROM blog WHERE id Like :blogId")
//    fun loadById(blogId: Long): Blog
    @Insert
    fun insert(blog: BlogSubs)

    @Insert
    fun insertAll(blog: List<BlogSubs>)
    @Update
    fun update(blog: BlogSubs)
    @Delete
    fun delete(blog: BlogSubs)
}