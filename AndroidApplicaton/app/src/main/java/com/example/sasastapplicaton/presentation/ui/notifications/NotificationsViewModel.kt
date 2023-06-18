package com.example.sasastapplicaton.presentation.ui.notifications

import android.app.Application
import android.os.SystemClock
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sasastapplicaton.data.db.database.AppDatabase
import com.example.sasastapplicaton.data.db.entity.Blog
import com.example.sasastapplicaton.data.db.entity.BlogSubs
import com.example.sasastapplicaton.data.db.entity.Subs
import com.example.sasastapplicaton.data.network.jwt.Provider
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class NotificationsViewModel(application: Application) : AndroidViewModel(application) {
    val db = AppDatabase.getInstance(application.applicationContext)
    val adapter = OkHttpClient()

    var listSubs = db.subsDao().getAll()
    var listBlog: LiveData<List<Blog>> = db.BlogDao().getAll()
    var listBlogSubs: List<BlogSubs>? = null

    fun getData() : LiveData<List<BlogSubs>> {
        return db.BlogSubsDao().getAll()
    }

    val gson = Gson()

    fun checked(blogSubs: BlogSubs) {

        //Если это подписка
        if(blogSubs.isPodpis) {
            val subs = Subs((0..100).random().toLong(), Provider.getInstanceUserUID()!!, blogSubs.blogId)

            //Добавление в БД
            CoroutineScope(Dispatchers.IO).launch {
                db.subsDao().insert(subs)
            }

            //Добавление на сервер
            CoroutineScope(Dispatchers.IO).launch {
                val mediaType = "application/json; charset=utf-8".toMediaType()
                val authRequestJson = gson.toJson(subs)
                println(authRequestJson.toString())
                val body = authRequestJson.toString().toRequestBody(mediaType)
                val requestPost = Request.Builder().post(body).header("Authorization", Provider.getInstanceJWT()!!)
                    .url("http://192.168.77.64:8080/api/v1/subs/").build()
                val response  = adapter.newCall(requestPost).execute()
            }
        }
        //Если это отписка
        else {
            //Удаление из БД
            val subs = Subs(-1, Provider.getInstanceUserUID()!!, blogSubs.blogId)
            CoroutineScope(Dispatchers.IO).launch {
               db.subsDao().deletePlus(Provider.getInstanceUserUID()!!, blogSubs.blogId)
            }

            //Удаление из сервера
            CoroutineScope(Dispatchers.IO).launch {
                val requestDel = Request.Builder().delete().header("Authorization", Provider.getInstanceJWT()!!)
                    .url("http://192.168.77.64:8080/api/v1/subs/plus/"
                            + Provider.getInstanceUserUID()!! + "/"+blogSubs.blogId).build()
                val response  = adapter.newCall(requestDel).execute()
            }
        }
    }


//    init {
//        CoroutineScope(Dispatchers.IO).launch {
//            listBlog = db.BlogDao().getAll().value?.filter { it.userUID == Provider.getInstanceUserUID() }
//        }
//        CoroutineScope(Dispatchers.IO).launch {
//            listSubs = db.subsDao().getAll().value?.filter { it.userUid == Provider.getInstanceUserUID() }
//        }
//        SystemClock.sleep(1200)
//        listBlogSubs = listBlog?.map { BlogSubs(-1, it.id, it.nameBlog, listSubs?.map { it.blogId }
//                ?.contains(it.id) == true) }
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                db.BlogSubsDao().insertAll(listBlogSubs!!)
//            }
//           catch (e: Exception) {}
//        }
//        }
    }
