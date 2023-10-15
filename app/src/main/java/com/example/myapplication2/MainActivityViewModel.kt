package com.example.myapplication2

import android.app.Application
import android.content.Context
import androidx.core.content.contentValuesOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solar_cells_v3.cellList

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    var cellList:cellList = cellList(context)

}