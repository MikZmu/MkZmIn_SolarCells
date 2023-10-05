package com.example.myapplication2.ui.cells

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.MainActivity
import com.example.myapplication2.databinding.FragmentCellsBinding
import com.example.solar_cells_v3.cellList
import com.jjoe64.graphview.GraphView

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
        val cellViewModel =
            ViewModelProvider(this).get(cellViewModel::class.java)

        _binding = FragmentCellsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var listOfCells:cellList = MainActivity.get
        var graph = binding.cellGraph
        graph.addSeries(listOfCells.getCell(listOfCells.listCells().get(0)).returnAsDataPoints())
        val cellData:TextView = binding.cellData
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}