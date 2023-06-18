package com.example.sasastapplicaton.data.network.dto

import androidx.room.ColumnInfo
import com.example.sasastapplicaton.data.db.entity.Role

data class UserRequest (
    val uid: Long,
    val login: String,
    val password: String,
    val gender: Boolean,
   val phoneNumber: Long,
     val birthDate: String,
    val registerDate: String,
    val role: Role
    )

