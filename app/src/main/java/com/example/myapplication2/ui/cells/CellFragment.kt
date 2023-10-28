package com.example.myapplication2.ui.cells

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.databinding.FragmentCellsBinding
import com.example.myapplication2.helper
import com.example.solar_cells_v3.Matrix_Cell
import com.example.solar_cells_v3.cellList
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class CellFragment : Fragment() {



    private var _binding: FragmentCellsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentCellsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var graph = binding.cellGraph
        var cl: cellList? = context?.let { cellList(it) }
        var cVMF: cellVMFactory? = cl?.let { cellVMFactory(it) }
        var cVM: cellViewModel? = cVMF?.let { ViewModelProvider(this, it).get(cellViewModel::class.java) }
        if (cVM != null) {
            val arrayAdapter: ArrayAdapter<*>
            arrayAdapter = context?.let { ArrayAdapter(it, android.R.layout.select_dialog_singlechoice, cVM.cl.listCells()) }!!
            var listOfCells = binding.cellList
            var uoc = binding.uoc
            var isc = binding.isc
            var mp = binding.mp
            var ump = binding.ump
            var imp = binding.imp
            listOfCells.choiceMode = ListView.CHOICE_MODE_SINGLE
            listOfCells.adapter = arrayAdapter





            listOfCells.setOnItemClickListener{ adapterView: AdapterView<*>, view2: View, i: Int, l: Long ->
                var selected = listOfCells.getItemAtPosition(i).toString()
                graph.removeAllSeries()
                var series: LineGraphSeries<DataPoint?> = helper.cellList.getCell(selected).returnYAsDataPoints()
                graph.addSeries(series)
                series.setColor(Color.RED)
                var UOC = Matrix_Cell.getUoc(helper.cellList.getCell(selected).xGridArray)
                var ISC = Matrix_Cell.getIsc(helper.cellList.getCell(selected).yGridArray)
                var MP = Matrix_Cell.getMp(helper.cellList.getCell(selected).normalisedXArray,cVM.cl.getCell(selected).yGridArray )
                var UMP = Matrix_Cell.getUMp(helper.cellList.getCell(selected).normalisedXArray,cVM.cl.getCell(selected).yGridArray )
                var IMP = Matrix_Cell.getIMp(helper.cellList.getCell(selected).normalisedXArray,cVM.cl.getCell(selected).yGridArray )
                uoc.setText(String.format("Uoc = %.2f V", UOC))
                isc.setText(String.format("Isc = %.2f A", ISC))
                mp.setText(String.format("MP = %.2f W", MP))
                ump.setText(String.format("Ump = %.2f V", UMP))
                imp.setText(String.format("Imp = %.2f A", IMP))

            }
        }



        return root

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}