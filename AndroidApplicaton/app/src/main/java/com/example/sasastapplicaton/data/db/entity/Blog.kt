package com.example.sasastapplicaton.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Blog (
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "user_UID") val userUID: Long,
    @ColumnInfo(name = "name_Blog") val nameBlog: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "creation_date") val creationDate: String
        )