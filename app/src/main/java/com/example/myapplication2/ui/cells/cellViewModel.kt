package com.example.myapplication2.ui.cells

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solar_cells_v3.cellList

class cellViewModel(celllist:cellList) : ViewModel() {
    var cl:cellList = celllist
    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }


    val text: LiveData<String> = _text
}