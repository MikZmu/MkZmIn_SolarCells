package com.example.myapplication2.ui.cells

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solar_cells_v3.cellList

class cellVMFactory(private var cl:cellList):ViewModelProvider.NewInstanceFactory(){


    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return cellViewModel(cl) as T
    }

}


