package com.example.myapplication2

import com.example.solar_cells_v3.Matrix_Cell
import com.example.solar_cells_v3.cellList
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class line(nodes:Pair<String,String>

) {

    var cells:ArrayList<Matrix_Cell> = ArrayList()
    var sum:ArrayList<Double> = ArrayList()
    var normalisedY:ArrayList<Double> = ArrayList()
    var dataPoints:LineGraphSeries<DataPoint?> = LineGraphSeries()
    var maxX:Double = 0.0
    var maxY:Double = 0.0
    var sortedX:ArrayList<Double> = ArrayList()
    var sortedY:ArrayList<Double> = ArrayList()


    fun add(cell:Matrix_Cell){
        this.cells.add(cell)
        this.sum = calculateSum2()
        this.normalisedY = this.normaliseYFun()
        this.maxX = this.maxX()
        this.maxY = this.maxY()
        this.sortedX = pairToList(0)
        this.sortedY = pairToList(1)
        this.dataPoints = this.returnAsDataPoints()
    }

    fun maxX():Double{
        return this.sum.max()
    }

    fun maxY():Double{
        var max = 0.0
        for(i in this.cells){
            if(i.yGridArray.max() > max){
                max = i.normalizedYArray.max()
            }
        }
        return max
    }

    fun longestY():Int{
        var returnVal:Int = 0

        for(i in this.cells){
            if(i.yGridArray.size > returnVal){
                returnVal = i.yGridArray.size
            }
        }
        return returnVal

    }

    fun longestYindex():Int{
        var returnVal:Int = 0
        var returnIndex:Int = 0
        for(i in this.cells){
            if(i.normalisedXArray.size > returnVal){
                returnVal = i.normalisedXArray.size
                returnIndex = this.cells.indexOf(i)
            }
        }
        return returnIndex

    }

    fun longestX():Int{
        var returnVal:Int = 0
        for(i in this.cells){
            if(i.normalisedXArray.size > returnVal){
                returnVal = i.normalisedXArray.size
            }
        }
        return returnVal

    }

    fun longestIndex():Int{
        var returnIndex:Int = 0
        var returnVal:Int = 0
        for(i in this.cells){
            if(i.normalisedXArray.size > returnVal){
                returnVal = i.normalisedXArray.size
                returnIndex = this.cells.indexOf(i)
            }
        }
        return returnIndex
    }

    fun pairToList(num:Int):ArrayList<Double>{
        var retList:ArrayList<Double> = ArrayList()
        if(num==0){
            for(i in sortData()){
                retList.add(i.first)
            }
        }
        if(num==1){
            for(i in sortData()){
                retList.add(i.second)
            }
        }
        return  retList
    }

    fun getUo():Double{

        return this.calculateSum().max()
    }

    fun getIsc():Double{

        return this.cells.get(longestIndex()).normalizedYArray.get(0)
    }

    fun getImp():Double{
        var helpArray:ArrayList<Double> = this.calculateSum()
        var stopper:Int = 0
        var pMax:Double = 0.0
        var Imp:Double = 0.0
        while (stopper<helpArray.size){
            var power = helpArray[stopper] * this.cells.get(longestIndex()).yGridArray.get(stopper)
            if(power>pMax){
                pMax=power
                Imp = this.cells.get(longestIndex()).yGridArray.get(stopper)
            }

        }
        return Imp

    }


    fun getUmp():Double{
        var helpArray:ArrayList<Double> = this.sum
        var stopper:Int = 0
        var pMax:Double = 0.0
        var Ump:Double = 0.0
        while (stopper<helpArray.size){
            var power = helpArray[stopper] * this.cells.get(longestIndex()).yGridArray.get(stopper)
            if(power>pMax){
                pMax=power
                Ump = helpArray.get(stopper)
            }

        }
        return Ump
    }

    fun getMp():Double{
        var helpArray:ArrayList<Double> = this.sum
        var stopper:Int = 0
        var pMax:Double = 0.0
        while (stopper<helpArray.size){
            var power = helpArray[stopper] * this.cells.get(longestIndex()).yGridArray.get(stopper)
            if(power>pMax){
                pMax=power
            }

        }
        return pMax
    }



    fun calculateSum():ArrayList<Double>{
        var stopper = 0
        var sumArray:ArrayList<Double> = ArrayList()

        var longest = this.cells.get(longestYindex()).yGridArray.size
        while (stopper<longest){
            sumArray.add(0.0)
            for(i in this.cells){
                sumArray[stopper] += i.normalisedXArray.getOrElse(stopper){0.0}
            }
            stopper++
        }
        var rtl:ArrayList<Double> = ArrayList(sumArray.sorted())
        return rtl



    }

    fun calculateSum2():ArrayList<Double>{
        var sumArray:ArrayList<Double> = ArrayList()
        var longest = this.cells.get(longestYindex()).yGridArray.size
        var stopper = 0
        while (stopper<longest){
            sumArray.add(0.0)
            for(i in this.cells){
                sumArray[stopper] += i.normalisedXArray.getOrElse(i.normalisedXArray.size-stopper){0.0}
            }
            stopper++
        }
        var rtl:ArrayList<Double> = ArrayList(sumArray.sorted())
        return rtl
    }

    fun normaliseYFun():ArrayList<Double>{
        var returnArray:ArrayList<Double> = ArrayList()
        returnArray = Matrix_Cell.normaliseYFun(this.sum, Matrix_Cell.gridX(sum, 0.01, sum.max()), this.cells.get(longestIndex()).yGridArray)
        returnArray.add(0.0)
        return returnArray
    }

    fun sortData() : ArrayList<Pair<Double,Double>>{
        var i =0
        var listOfPairs:ArrayList<Pair<Double,Double>> = ArrayList()
        var ygrid:ArrayList<Double> = this.cells.get(this.longestIndex()).yGridArray
        while(i < this.sum.size){
            listOfPairs.add(Pair(sum[i], ygrid.get(i)))
            i++
        }
        listOfPairs.sortWith(compareBy<Pair<Double, Double>>({it.first}))
        return listOfPairs

    }



    fun returnAsDataPoints():LineGraphSeries<DataPoint?> {
        var yReturnArray: ArrayList<Double> = this.cells.get(longestYindex()).yGridArray
        var dataArray: Array<DataPoint?> = arrayOfNulls<DataPoint>(sortedX.size.toInt())
        var i = 0
        while (i < sum.size) {
            var dummyDP: DataPoint = DataPoint(sum[i], yReturnArray.get(i))
            dataArray[i] = dummyDP
            i++
        }
        val returnSeries: LineGraphSeries<DataPoint?> = LineGraphSeries(dataArray)
        return returnSeries

    }
}
