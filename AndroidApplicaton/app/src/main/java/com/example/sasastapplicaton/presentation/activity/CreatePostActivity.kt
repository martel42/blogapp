package com.example.sasastapplicaton.presentation.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.sasastapplicaton.R
import com.example.sasastapplicaton.data.db.database.AppDatabase
import com.example.sasastapplicaton.data.db.entity.Post
import com.example.sasastapplicaton.data.network.jwt.Provider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.time.LocalDateTime

class CreatePostActivity: AppCompatActivity() {
    val adapter = OkHttpClient()

    val gson = Gson()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        val title: EditText = findViewById(R.id.createTitle)
        val content: EditText = findViewById(R.id.createPost)
        val premium: SwitchCompat = findViewById(R.id.createPremium)
        val button: Button = findViewById(R.id.buttonCreatePost)

        //База данных
        val db = AppDatabase.getInstance(context = applicationContext)

        button.setOnClickListener{
            val titlePost = title.text.toString()
            val contentPost = content.text.toString()
            val premiumPost = premium.isChecked
            val newPost = Post((1..1000).random().toLong(), Provider.getInstanceBlogUID()!!, titlePost, contentPost,
                LocalDateTime.now().toString(), premiumPost)

            //Сохранение в БД
            CoroutineScope(Dispatchers.IO).launch {
                db.postDao().insert(newPost)
            }

            //Сохранение на сервер
            CoroutineScope(Dispatchers.IO).launch {
                val mediaType = "application/json; charset=utf-8".toMediaType()
                val postRequestJson = gson.toJson(newPost)
                val body = postRequestJson.toString().toRequestBody(mediaType)
                val requestPost = Request.Builder().post(body).header("Authorization", Provider.getInstanceJWT()!!)
                    .url("http://192.168.77.64:8080/api/v1/post/").build()
                val response  = adapter.newCall(requestPost).execute()
            }

            SystemClock.sleep(1000)
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            finish()
        }
    }
}