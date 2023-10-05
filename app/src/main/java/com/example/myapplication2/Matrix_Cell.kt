package com.example.solar_cells_v3
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.PointsGraphSeries
import com.jjoe64.graphview.series.Series
import kotlin.math.abs


class Matrix_Cell(val name: String,
                  val xArray:ArrayList<Double>,
                  yArray:ArrayList<Double>,
                  step:Double =0.1,
                  val xGridArray:ArrayList<Double> = gridX(xArray,step,xArray.max()),
                  val yGridArray: ArrayList<Double> = gridY(yArray, step, yArray.max()),
                  val normalizedYArray:ArrayList<Double> = normalizeY(xArray,xGridArray,yArray,step),
                  val yGridNormalised:ArrayList<Double> = gridNormaliseY(normalizedYArray, yGridArray)

)
 {


    fun returnName():String{
        return this.name
    }

    fun returnAsString():String{
        var i = 0
        var returnString:String =""
        while(i < xGridArray.size){
            returnString+=this.xGridArray.get(i).toString() + ":" + this.normalizedYArray.get(i)+"\n"
            i++
        }
        return returnString

    }

    fun returnAsDataPoints():Series<DataPoint?>{
        var i = 0
        var dataArray:Array<DataPoint?> = arrayOfNulls<DataPoint>(xGridArray.size.toInt())
        while (i<this.xGridArray.size){
            var dummyDP:DataPoint = DataPoint(this.xGridArray.get(i), this.yGridNormalised.get(i))
            dataArray[i] = dummyDP
            i++
        }
        val returnSeries:Series<DataPoint?> = PointsGraphSeries(dataArray)
        return returnSeries
    }




    companion object {

        fun gridX(
            xArray: ArrayList<Double>,
            step: Double,
            upperBound: Double): ArrayList<Double> {
            var i: Double = 0.toDouble()
            var index: Int = 0
            var xArrayNormalised: ArrayList<Double> = ArrayList()

            while (i < upperBound) {
                xArrayNormalised.add(index, i)
                i += step
                index +=1
            }
            return xArrayNormalised

        }

        fun gridY(yArray: ArrayList<Double>, step: Double, upperBound: Double): ArrayList<Double> {
            var i: Double = 0.toDouble()
            var index: Int = 0;
            var yArrayNormalised: ArrayList<Double> = ArrayList()

            while (i < upperBound) {
                yArrayNormalised.add(index, i)
                i += step
                index +=1
            }
            return yArrayNormalised

        }

        fun normalizeY( xArray:ArrayList<Double>,xArrayNormalised: ArrayList<Double>,yArray: ArrayList<Double>,step: Double): ArrayList<Double> {
            var yVal:Double
            var yArrayNormalised: ArrayList<Double> = ArrayList()
            for (x in xArrayNormalised) {
                yVal=yArray.get(0)
                var lesser = lesserVal(x, xArray)
                var greater = greaterVal(x, xArray)
                var lesserIndex = xArray.indexOf(lesser)
                var greaterIndex = xArray.indexOf(greater)

                var a = (yArray.get(greaterIndex) - yArray.getOrElse(lesserIndex){yArray.get(0)}.toDouble()) / (greater - lesser)
                var b = yArray.getOrElse(lesserIndex){yArray.get(0)} - a*lesser
                var  yVal = a * lesser + b
                yArrayNormalised.add(yVal)

            }
            yArrayNormalised[yArrayNormalised.size-1] = 0.toDouble()
            return yArrayNormalised
        }

        fun gridNormaliseY(arrayPre:ArrayList<Double>,grid:ArrayList<Double>):ArrayList<Double>{
            var returnArray:ArrayList<Double> = ArrayList()
            for(i in arrayPre){
                returnArray.add(closestValue(i, grid))
            }
            return returnArray
        }

        fun closestValue(value: Double, list:ArrayList<Double>):Double{
            var difference: Double = abs(list[0] - value)
            var difference2:Double
            var index:Int = 0
            var index2:Int =0
            while(index<list.size){
                difference2 = (abs(list[index] - value))
                if(difference2<=difference){
                    index2 = index
                    difference = difference2
                }
                index++
            }
            return list.get(index2)
        }

        fun lesserVal(value: Double, dataSet: ArrayList<Double>): Double {
            var prevX: Double = dataSet[0].toDouble()


            for (x in dataSet) {
                if (x >= value) {
                    if(dataSet.indexOf(x)==0){
                        return 0.toDouble()
                    }
                    return prevX
                }
                prevX = x
            }
            return prevX

        }

        fun greaterVal(value: Double, dataSet: ArrayList<Double>): Double {
            for (x in dataSet) {
                if (x > value) {
                    return x
                }
            }
            return dataSet.get(0)
        }




    }
}