package com.example.myapplication2

import com.example.solar_cells_v3.Matrix_Cell
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries

class circuit() {

    var lines:ArrayList<line> = ArrayList()
    var sum: ArrayList<Double> = ArrayList()
    var dataPoints:LineGraphSeries<DataPoint?> = LineGraphSeries()
    var maxX:Double =0.0
    var maxY:Double=0.0


    fun longest():Int{
        var returnVal:Int = 0

        for(i in this.lines){
            var normalisedArr:ArrayList<Double> = Matrix_Cell.gridX(i.calculateSum(), 0.01, i.calculateSum().max())
            if(normalisedArr.size > returnVal){
                returnVal = normalisedArr.size
            }
        }
        return returnVal

    }

    fun add(line:line){
        this.lines.add(line)
        this.sum = this.calculateSum()
        this.dataPoints = this.returnAsDataPoints()
        this.maxX = this.maxX()
        this.maxY = this.maxY()

    }


    fun longestIndex():Int{
        var returnIndex:Int = 0
        var returnVal:Int = 0
        for(i in this.lines){
            var normalisedArr:ArrayList<Double> = Matrix_Cell.gridX(i.calculateSum(), 0.01, i.calculateSum().max())
            if(normalisedArr.size > returnVal){
                returnVal = normalisedArr.size
                returnIndex = this.lines.indexOf(i)
            }
        }
        return returnIndex
    }

    fun maxY():Double{
        return this.sum.max()
    }

    fun maxX():Double{
        var max = 0.0
        for(i in lines){
            if(i.sum.max()>max){
                max = i.sum.max()
            }
        }
        return max
    }

    fun calculateSum():ArrayList<Double> {
        var stopper: Int = 0
        var sumArray: ArrayList<Double> = ArrayList()
        var longest = this.longest()
        while (stopper < longest) {
            var sum: Double = 0.toDouble()
            for (i in this.lines) {
                sum += i.normalisedY.getOrElse(stopper){0.0}
            }
            sumArray.add(sum)
            stopper++

        }

        return sumArray
    }

    fun returnAsDataPoints(): LineGraphSeries<DataPoint?> {
        var yReturnArray:ArrayList<Double> = calculateSum()
        var xReturnArray:ArrayList<Double> = Matrix_Cell.gridX(this.lines.get(this.longestIndex()).calculateSum(),0.01,this.lines.get(this.longestIndex()).calculateSum().max())
        var longest = xReturnArray.size
        var dataArray:Array<DataPoint?> = arrayOfNulls<DataPoint>(longest)
        var i = 0
        while (i<longest){
            var dummyDP: DataPoint = DataPoint(xReturnArray.get(i), yReturnArray.get(i))
            dataArray[i] = dummyDP
            i++
        }
        val returnSeries: LineGraphSeries<DataPoint?> = LineGraphSeries(dataArray)
        return returnSeries


    }







}