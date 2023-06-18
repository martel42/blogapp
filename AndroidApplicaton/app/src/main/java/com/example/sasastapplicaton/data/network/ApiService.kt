package com.example.sasastapplicaton.data.network

import com.example.sasastapplicaton.data.db.entity.User
import com.example.sasastapplicaton.data.network.dto.AuthRequest
import com.example.sasastapplicaton.data.network.dto.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("dummy")
    suspend fun dummyResponse() : String

    @POST("login/")
    suspend fun auth(@Body request: AuthRequest): AuthResponse

    @GET("user/all")
    suspend fun getAllUser(@Header("Authorization") token: String) :List<User>

    @POST("user/")
    suspend fun addUser(@Body userResponse: User)
}