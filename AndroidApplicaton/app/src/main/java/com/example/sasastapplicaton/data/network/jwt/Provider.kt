package com.example.sasastapplicaton.data.network.jwt

//Класс, хранящий JWT и Login
object Provider {
    var jwt: String? = null
    var userLogin: String? = null
    var blogUID:  Long? = null
    var userUID:  Long? = null

    @Synchronized
    fun getInstanceJWT(): String? {
        return jwt
    }
    @Synchronized
    fun setInstanceJWT(newInstance: String) {
       jwt = newInstance
    }

    @Synchronized
    fun getInstanceUserLogin(): String? {
        return userLogin
    }
    @Synchronized
    fun setInstanceUserLogin(newInstance: String) {
        userLogin = newInstance
    }

    @Synchronized
    fun getInstanceBlogUID(): Long? {
        return blogUID
    }
    @Synchronized
    fun setInstanceBlogUID(newInstance: Long) {
        blogUID = newInstance
    }

    @Synchronized
    fun getInstanceUserUID(): Long? {
        return userUID
    }
    @Synchronized
    fun setInstanceUserUID(newInstance: Long) {
        userUID = newInstance
    }
}