package com.example.myapplication2.ui.home

import android.R
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.circuit
import com.example.myapplication2.databinding.ColumnBinding
import com.example.myapplication2.databinding.FragmentCircuitBinding
import com.example.myapplication2.line
import com.example.myapplication2.ui.cells.cellVMFactory
import com.example.myapplication2.ui.cells.cellViewModel
import com.example.solar_cells_v3.cellList
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries


class circuitFragment : Fragment() {

    private var _binding: FragmentCircuitBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)



        _binding = FragmentCircuitBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fun getBtns():ArrayList<ArrayList<ImageButton>>{
            var buttons:ArrayList<ArrayList<ImageButton>> = ArrayList()
            val c0:View = binding.c0.linearLayout
            val c1:View = binding.c1.linearLayout
            val c2:View = binding.c2.linearLayout
            val c3:View = binding.c3.linearLayout
            val c4:View = binding.c4.linearLayout

            fun toRow(view: View):ArrayList<ImageButton>{
                var btnList:ArrayList<ImageButton> = ArrayList()
                for(xedni in 0 until 20){
                    var rowtag = "r" + xedni.toString()
                    var btn = view.findViewWithTag<ImageButton>(rowtag)
                    btnList.add(btn)
                }
                return btnList
            }
            buttons.add(toRow(c0))
            buttons.add(toRow(c1))
            buttons.add(toRow(c2))
            buttons.add(toRow(c3))
            buttons.add(toRow(c4))


            return buttons
        }
        var buttons = getBtns()



        /*
        val graph = binding.graph
        var cl: cellList? = context?.let { cellList(it) }
        var cVMF: cellVMFactory? = cl?.let { cellVMFactory(it) }
        var cVM: cellViewModel? =
            cVMF?.let { ViewModelProvider(this, it).get(cellViewModel::class.java) }
        if (cVM != null) {
            graph.addSeries(cVM.cl.getCell(cVM.cl.listCells().get(0)).returnXAsDataPoints())

            val arrayAdapter: ArrayAdapter<*>
            var list: ArrayList<String> = ArrayList()
            list.add("line1")
            list.add("line2")
            list.add("line3")
            arrayAdapter =
                context?.let { ArrayAdapter(it, R.layout.select_dialog_singlechoice, list) }!!
            var cellList = binding.cellList
            cellList.choiceMode = ListView.CHOICE_MODE_SINGLE
            cellList.adapter = arrayAdapter

            var line: line = line(Pair("A", "B"))
            line.add(cVM.cl.getCell(cVM.cl.listCells().get(2)))
            line.add(cVM.cl.getCell(cVM.cl.listCells().get(1)))

            var line2: line = line(Pair("B", "C"))
            line2.add(cVM.cl.getCell(cVM.cl.listCells().get(2)))
            line2.add(cVM.cl.getCell(cVM.cl.listCells().get(0)))

            var line3: line = line(Pair("C", "D"))
            line3.add(cVM.cl.getCell(cVM.cl.listCells().get(2)))

            var circ:circuit = circuit()
            circ.add(line)
            circ.add(line2)
            circ.add(line3)

            //var circ:circuit = circuit()
            //circ.add(line)
            //circ.add(line2)


            cellList.setOnItemClickListener { adapterView: AdapterView<*>, view2: View, i: Int, l: Long ->
                var selected = cellList.getItemAtPosition(i).toString()
                graph.removeAllSeries()
                if (selected.equals("line1")) {
                    var series: LineGraphSeries<DataPoint?> = line.dataPoints
                    graph.viewport.setMaxY(line.maxY * 1.1)
                    graph.viewport.setMaxX(line.maxX * 1.1)
                    graph.getViewport().setYAxisBoundsManual(true);
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.addSeries(series)
                    series.setColor(Color.RED)
                } else if (selected.equals("line2")) {
                    var series: LineGraphSeries<DataPoint?> = line2.dataPoints
                    graph.viewport.setMaxY(line2.maxY * 1.1)
                    graph.viewport.setMaxX(line2.maxX * 1.1)
                    graph.getViewport().setYAxisBoundsManual(true)
                    graph.getViewport().setXAxisBoundsManual(true)
                    graph.addSeries(series)
                    series.setColor(Color.GREEN)
                } else if (selected.equals("line3")) {
                    var series: LineGraphSeries<DataPoint?> = circ.dataPoints
                    graph.viewport.setMaxY(circ.maxY * 1.1)
                    graph.viewport.setMaxX(circ.maxX * 1.1)
                    graph.getViewport().setYAxisBoundsManual(true);
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.addSeries(series)
                    series.setColor(Color.MAGENTA)


                }


            }
         */



            return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        fun getChildren(layout: LinearLayout):ArrayList<View>{
            val count = layout.childCount
            var rtList:ArrayList<View> = ArrayList()
            for(index in 0 until count){
                val child = layout.getChildAt(index)
                rtList.add(child)
            }
            return rtList

        }

        fun getChildren2(list:ArrayList<View>, index:Int): View {
            val count = list.size
            var rtList:ArrayList<View> = ArrayList()
            for(index in 0 until count){
                val child = list.get(index)
                rtList.add(child)
            }
            return rtList[index]
        }


        fun getChildren3(list:ArrayList<View>): ArrayList<View> {
            val count = list.size
            var rtList:ArrayList<View> = ArrayList()
            for(index in 0 until count){
                val child = list.get(index)
                rtList.add(child)
            }
            return rtList
        }


    }
}
