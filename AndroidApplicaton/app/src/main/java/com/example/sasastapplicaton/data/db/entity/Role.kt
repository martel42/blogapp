package com.example.sasastapplicaton.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Role (
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "authority") val authority: String
    )