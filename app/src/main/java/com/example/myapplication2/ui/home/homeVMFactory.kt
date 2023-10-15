package com.example.myapplication2.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.MainActivityViewModel
import java.lang.IllegalArgumentException

class homeVMFactory (application: Application): ViewModelProvider.AndroidViewModelFactory(Application()) {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return HomeViewModel(Application()) as T
        }
        throw IllegalArgumentException("blabla")
    }
}