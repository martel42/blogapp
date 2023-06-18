package com.example.sasastapplicaton.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sasastapplicaton.R
import com.example.sasastapplicaton.data.db.database.AppDatabase
import com.example.sasastapplicaton.data.db.entity.Blog
import com.example.sasastapplicaton.data.db.entity.BlogSubs
import com.example.sasastapplicaton.data.db.entity.Post
import com.example.sasastapplicaton.data.db.entity.Subs
import com.example.sasastapplicaton.data.db.entity.User
import com.example.sasastapplicaton.data.db.entity.UserSubs
import com.example.sasastapplicaton.data.network.dto.AuthRequest
import com.example.sasastapplicaton.data.network.dto.AuthResponse
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
import java.lang.Exception


class LoginActivity : AppCompatActivity() {

    private lateinit var regButton: TextView
    lateinit var login: EditText
    private lateinit var pass: EditText
    lateinit var btnLogin: Button

    val adapter = OkHttpClient()

    val gson = Gson()

    private val requestScope = CoroutineScope(Dispatchers.IO)
    private val dbAddDataScope = CoroutineScope(Dispatchers.IO)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //База данных
        val db = AppDatabase.getInstance(context = applicationContext)
        val userDao = db.userDao()

        // View Binding
        regButton = findViewById(R.id.regisLogin)
        btnLogin = findViewById(R.id.buttonLogin)
        login = findViewById(R.id.loginLogin)
        pass = findViewById(R.id.passwordLogin)

        //Авторизация
        btnLogin.setOnClickListener {
            //Берем данные
            val login = login.text.toString()
            val pass = pass.text.toString()

            //Создаем запрос
            val authRequest = AuthRequest(login, pass)
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val authRequestJson = gson.toJson(authRequest)
            val body = authRequestJson.toString().toRequestBody(mediaType)
            val requestLogin = Request.Builder().post(body)
                .url("http://192.168.77.64:8080/api/v1/login/").build()

            //Выпала ошибка при запросе?
            var isError = false

            //Запрос
            requestScope.launch {
                val response  = adapter.newCall(requestLogin).execute()
                if (response.code == 202) {
                    val jwt: String = gson.fromJson(response.body?.string(), AuthResponse::class.java).token
                    Provider.setInstanceJWT("Bearer " + jwt)
                    Provider.setInstanceUserLogin(login)
                }
                else {
                    isError = true
                }
            }

            btnLogin.isActivated = false
            SystemClock.sleep(1000);

            //Если ошибка, то сообщаем это и даем еще раз попробовать
            if (isError) {
                val toast = Toast.makeText(applicationContext,
                    "Что-то пошло не так! Повторите попытку!", Toast.LENGTH_SHORT)
                toast.show()
                btnLogin.isActivated = true
            }
            //Если нет, то выгружаем данные у сервера и грузим в БД
            else {
                //Загрузка пользователей
                val requestUser = Request.Builder().get().header("Authorization", Provider.getInstanceJWT()!!)
                    .url("http://192.168.77.64:8080/api/v1/user/all").build()
                val userType = object : TypeToken<ArrayList<User?>?>() {}.type

                CoroutineScope(Dispatchers.IO).launch {
                    val response = adapter.newCall(requestUser).execute()
                    if (response.code == 200) {
                        val userList: List<User> = gson.fromJson(response.body?.string(), userType)
                        println(userList)
                        try {
                            Provider.setInstanceUserUID(userList.find { it.login == Provider.getInstanceUserLogin() }!!.uid)
                        }
                        catch (e: Exception) {}
                        CoroutineScope(Dispatchers.IO).launch {
                            userDao.insertAll(userList)
                        }
                    } else {
                        isError = true
                    }
                }


                    //Загрузка блогов
                    val requestBlog = Request.Builder().get().header("Authorization", Provider.getInstanceJWT()!!)
                        .url("http://192.168.77.64:8080/api/v1/blog/all").build()
                    val blogType = object : TypeToken<ArrayList<Blog?>?>() {}.type

                    CoroutineScope(Dispatchers.IO).launch {
                        val response = adapter.newCall(requestBlog).execute()
                        if (response.code == 200) {
                            val blogList: List<Blog> =
                                gson.fromJson(response.body?.string(), blogType)
                            println(blogList)
                            try {
                                Provider.setInstanceBlogUID(blogList.find { it.userUID == Provider.getInstanceUserUID() }!!.id)
                            }
                            catch (e: Exception) {}
                            CoroutineScope(Dispatchers.IO).launch {
                                db.BlogDao().insertAll(blogList)
                            }
                        } else {
                            isError = true
                        }
                    }


                        //Загрузка постов
                        val requestPost = Request.Builder().get().header("Authorization", Provider.getInstanceJWT()!!)
                            .url("http://192.168.77.64:8080/api/v1/post/all").build()
                        val postType = object : TypeToken<ArrayList<Post?>?>() {}.type

                        CoroutineScope(Dispatchers.IO).launch {
                            val response = adapter.newCall(requestPost).execute()
                            if (response.code == 200) {
                                val postList: List<Post> =
                                    gson.fromJson(response.body?.string(), postType)
                                println(postList)
                                CoroutineScope(Dispatchers.IO).launch {
                                    db.postDao().insertAll(postList)
                                }
                            } else {
                                isError = true
                            }
                        }

                            //Загрузка подписок
                            val requestSubs = Request.Builder().get().header("Authorization", Provider.getInstanceJWT()!!)
                                .url("http://192.168.77.64:8080/api/v1/subs/all").build()
                            val subsType = object : TypeToken<ArrayList<Subs?>?>() {}.type
                            var subsList: List<Subs> = listOf()
                            CoroutineScope(Dispatchers.IO).launch {
                                val response = adapter.newCall(requestSubs).execute()
                                if (response.code == 200) {
                                    subsList = gson.fromJson(response.body?.string(), subsType)
                                    println(subsList)
                                    CoroutineScope(Dispatchers.IO).launch {
                                        db.subsDao().insertAll(subsList)
                                    }
                                } else {
                                    isError = true
                                }
                            }


                                //Загрузка подписок пользоваеля
                                val requestUserSubs = Request.Builder().get().header("Authorization", Provider.getInstanceJWT()!!)
                                    .url("http://192.168.77.64:8080/api/v1/userSubs/all").build()
                                val userSubsType = object : TypeToken<ArrayList<UserSubs?>?>() {}.type

                                CoroutineScope(Dispatchers.IO).launch {
                                    val response = adapter.newCall(requestUserSubs).execute()
                                    if (response.code == 200) {
                                        val userSubsList: List<UserSubs> =
                                            gson.fromJson(response.body?.string(), userSubsType)
                                        println(userSubsList)
                                        CoroutineScope(Dispatchers.IO).launch {
                                            db.userSubsDao().insertAll(userSubsList)
                                        }
                                    } else {
                                        isError = true
                                    }
                                }

            }

            //Ждем загрузки и переходим на главный экран
            SystemClock.sleep(2500)

            if (isError) {
                val toast = Toast.makeText(applicationContext,
                    "Что-то пошло не так! Повторите попытку!", Toast.LENGTH_SHORT)
                toast.show()
                btnLogin.isActivated = true
            }
            else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        //На окно регистрации
        regButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}