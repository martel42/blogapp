package com.example.sasastapplicaton.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.intellij.lang.annotations.Identifier
import java.time.LocalDateTime

@Entity
data class Post(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "blog_id") val blogId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "post_date") val postDate: String,
    @ColumnInfo(name = "premium") val premium: Boolean
)
