package com.example.sasastapplicaton.presentation.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sasastapplicaton.R
import com.example.sasastapplicaton.data.db.entity.Blog
import com.example.sasastapplicaton.data.db.entity.Role
import com.example.sasastapplicaton.data.network.dto.UserRequest
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.time.LocalDate

class RegisterActivity : AppCompatActivity() {
    lateinit var regButton: Button
    lateinit var birthDate: EditText
    lateinit var phoneNumber: EditText
    lateinit var lgn: EditText
    lateinit var pass: EditText
    lateinit var male: RadioButton
    lateinit var female: RadioButton


    val adapter = OkHttpClient()

    val gson = Gson()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // View Binding
        regButton = findViewById(R.id.buttonRegister)
        birthDate = findViewById(R.id.birthDateRegister)
        phoneNumber = findViewById(R.id.numberRegister)
        lgn = findViewById(R.id.loginRegister)
        pass = findViewById(R.id.passwordRegister)
        male  = findViewById(R.id.male)
        female = findViewById(R.id.female)


        //Регистрация
        regButton.setOnClickListener {
            //Берем данные
            val uid = (0..1000).random().toLong()
            val login = lgn.text.toString()
            val password = pass.text.toString()
            val gender = if (male.isChecked) true else if (female.isChecked) false else true
            val number = phoneNumber.text.toString().toIntOrNull() ?: 0
            val birth = birthDate.text.toString()
            val regiesterDate = LocalDate.now().toString()
            val role = Role(1, "user")
            val newUser = UserRequest(uid, login, password, gender, number.toLong(), birth, regiesterDate, role)

            println(newUser)
            //Создаем запрос
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val regRequestJson = gson.toJson(newUser)
            val bodyUser = regRequestJson.toString().toRequestBody(mediaType)
            val requestRegister = Request.Builder().post(bodyUser)
                .url("http://192.168.77.64:8080/api/v1/user/").build()

            //Выпала ошибка при запросе?
            var isError = false

            //Запрос на создание пользователя
            CoroutineScope(Dispatchers.IO).launch {
                val responseUser  = adapter.newCall(requestRegister).execute()
                if (responseUser.code == 201) {
                    //Создаем Блог пользователя
                    val newBlog = Blog(-1, uid, "Блог " + login, "Блог" , regiesterDate)
                    val regRequestBlog = gson.toJson(newBlog)
                    val bodyBlog = regRequestBlog.toString().toRequestBody(mediaType)
                    val requestBlog = Request.Builder().post(bodyBlog)
                        .url("http://192.168.77.64:8080/api/v1/blog/").build()
                    //Запрос на создание блога
                    CoroutineScope(Dispatchers.IO).launch {
                        val responseBlog  = adapter.newCall(requestBlog).execute()
                        if (responseBlog.code == 201) {}
                        else {
                            isError = true
                        }
                    }
                }
                else {
                    isError = true
                }
            }

            regButton.isActivated = false
            SystemClock.sleep(1500);
            //Если ошибка, то сообщаем это и даем еще раз попробовать
            if (isError) {
                val toast = Toast.makeText(applicationContext,
                    "Что-то пошло не так! Повторите попытку!", Toast.LENGTH_SHORT)
                toast.show()
                regButton.isActivated = true
            }
            else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                register()
                finish()
            }
        }

    }

    private fun register() {

    }


}