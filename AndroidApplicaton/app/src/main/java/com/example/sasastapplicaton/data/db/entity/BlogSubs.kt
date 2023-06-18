package com.example.sasastapplicaton.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BlogSubs (
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "blog_id") val blogId: Long,
    @ColumnInfo(name = "blog_des") val blogDes: String,
    @ColumnInfo(name = "podpis") var isPodpis: Boolean
    )