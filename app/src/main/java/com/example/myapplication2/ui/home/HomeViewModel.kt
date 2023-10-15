package com.example.myapplication2.ui.home
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solar_cells_v3.cellList

class HomeViewModel(application: Application): AndroidViewModel(Application()) {


    var cellList: cellList = cellList(application.applicationContext)


    private val _text = MutableLiveData<String>().apply {
        value = "This is circuit View"
    }
    val text: LiveData<String> = _text
}