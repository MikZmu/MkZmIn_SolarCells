package com.example.myapplication2.ui.graphs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class graphsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "this is Graphs View"
    }
    val text: LiveData<String> = _text
}