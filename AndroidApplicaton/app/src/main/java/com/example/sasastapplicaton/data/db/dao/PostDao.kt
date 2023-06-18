package com.example.sasastapplicaton.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sasastapplicaton.data.db.entity.Post

@Dao
interface PostDao {
    @Query("select * from post")
      fun getAll(): LiveData<List<Post>>
//    @Query("SELECT * FROM post WHERE id Like :postId")
//    fun loadById(postId: Long): Post
    @Insert
      fun insert(post: Post)
    @Insert
    fun insertAll(post: List<Post>)
    @Update
      fun update(post: Post)
    @Delete
      fun delete(post: Post)
}