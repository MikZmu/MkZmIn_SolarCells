package com.example.solar_cells_v3
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import androidx.core.util.rangeTo
import java.io.InputStream
import java.lang.Exception


class cellList (val context: Context, var cellList:ArrayList<Matrix_Cell> = getData(context)){


    fun listCells():String{
        var returnString:String = "";
        for(i in cellList){
            returnString += i.returnName();
        }
        return returnString

    }

    fun getCell(cellName:String):Matrix_Cell{
        for(i in this.cellList){
            if(i.returnName().equals(cellName)){
                return i
            }
        }
        throw Exception("kill me")

    }


    companion object {

        fun getData(context: Context):ArrayList<Matrix_Cell>{
            var returnList:ArrayList<Matrix_Cell> = ArrayList()
            var list = context.assets.list("");
            if (list != null) {
                for(i in list){
                    if(i.contains(".csv")){
                        var returnXArray = ArrayList<Double>()
                        var returnYArray = ArrayList<Double>()
                        val inputStream:InputStream = context.assets.open(i)
                        returnXArray.clear()
                        returnYArray.clear()
                        inputStream.bufferedReader().forEachLine {

                            returnXArray.add(it.replace(",",".").split(";")[0].toDouble())
                            returnYArray.add(it.replace(",",".").split(";")[1].toDouble())
                        }

                        returnList.add(Matrix_Cell(i,returnXArray, returnYArray))
                    }

                }
            }
            return returnList
    }


    }




}