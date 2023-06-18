package com.example.sasastapplicaton.data.db.entity

import androidx.room.*
import java.time.LocalDate

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo (name = "uid") val uid: Long,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "gender") val gender: Boolean,
    @ColumnInfo(name = "phone_number") val phoneNumber: Long,
    @ColumnInfo(name = "birth_date") val birthDate: String,
    @ColumnInfo(name = "register_date") val registerDate: String,
    @ColumnInfo(name = "role_id") val roleId: Long
)
