package com.example.myapplication2

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(application: Application):ViewModelProvider.AndroidViewModelFactory(Application()) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(Application()) as T
        }
        throw IllegalArgumentException("blabla")
    }


}