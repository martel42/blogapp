package com.example.sasastapplicaton.presentation.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sasastapplicaton.data.db.database.AppDatabase

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val db = AppDatabase.getInstance(application.applicationContext)
    val postList = db.postDao().getAll()
    val subsList = db.subsDao().getAll()
}