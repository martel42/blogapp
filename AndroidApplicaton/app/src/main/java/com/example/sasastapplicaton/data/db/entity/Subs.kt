package com.example.sasastapplicaton.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subs(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "user_id") val userUid: Long,
    @ColumnInfo(name = "user_subs_id") val blogId: Long
)
