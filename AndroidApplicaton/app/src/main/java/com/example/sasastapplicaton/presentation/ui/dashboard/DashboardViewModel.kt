package com.example.sasastapplicaton.presentation.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sasastapplicaton.data.db.database.AppDatabase
import com.example.sasastapplicaton.data.db.entity.Post
import com.example.sasastapplicaton.data.network.jwt.Provider
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    val db = AppDatabase.getInstance(application.applicationContext).postDao()
    val adapter = OkHttpClient()

    val gson = Gson()
    fun deletePost(post: Post?) {
        //Удаление из БД
        CoroutineScope(Dispatchers.IO).launch {
            db.delete(post!!)
        }

        //Удаление из сервера
        CoroutineScope(Dispatchers.IO).launch {
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val number = post!!.id.toString()
            val requestPost = Request.Builder().delete().header("Authorization", Provider.getInstanceJWT()!!)
                .url("http://192.168.77.64:8080/api/v1/post/" + number).build()
            val response  = adapter.newCall(requestPost).execute()
        }
    }

    val postList = db.getAll()
}