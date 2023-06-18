package com.example.sasastapplicaton.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserSubs(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "blog_id") val blogId: Long
)
