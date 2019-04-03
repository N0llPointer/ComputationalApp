package com.nollpointer.computationalapp.view

import android.content.Context
import android.support.v7.widget.CardView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.nollpointer.computationalapp.R

class TriangleComputationCard(context: Context): CardView(context) {

    private val resultTextView: TextView
    private val xValueEditText: EditText
    private val yValueEditText: EditText

    private val countTextView: TextView

    private val coordinateList: MutableList<Pair<Double,Double>>

    init {
        val view = View.inflate(context, R.layout.triangle_computation_card, null)
        addView(view)

        xValueEditText = view.findViewById(R.id.input_x)
        yValueEditText = view.findViewById(R.id.input_y)

        resultTextView = view.findViewById(R.id.result_text_view)
        countTextView = view.findViewById(R.id.count_text_view)

        coordinateList = mutableListOf()

        val addButton = view.findViewById<Button>(R.id.add_button)
        val clearButton = view.findViewById<Button>(R.id.clear_all_button)

        addButton.setOnClickListener {
            val x = xValueEditText.text.toString().toDouble()
            val y = yValueEditText.text.toString().toDouble()

            coordinateList.add(Pair(x,y))
            countTextView.text = "${coordinateList.size}"

            xValueEditText.setText("",TextView.BufferType.EDITABLE)
            yValueEditText.setText("",TextView.BufferType.EDITABLE)
        }

        clearButton.setOnClickListener {
            coordinateList.clear()
            countTextView.text = "${coordinateList.size}"
            resultTextView.text = ""
        }
    }



    fun calculate(){

        val list = mutableListOf<TrianglePerimetr>()
        for(i in coordinateList.withIndex()){
            for(j in coordinateList.withIndex()){
                if(i.index == j.index)
                    continue
                for(k in coordinateList.withIndex()){
                    if(k.index == i.index || k.index == j.index)
                        continue
                    val triangle = TrianglePerimetr(i.value,j.value,k.value)
                    if(!list.contains(triangle))
                        list.add(triangle)
                }
            }
        }

        var maxTriangle = list[0]
        for(triangle in list){
            if(triangle.compareTo(maxTriangle) > 0)
                maxTriangle = triangle
        }

        resultTextView.text = "A(${maxTriangle.firstCoordinate}\n" +
                "B(${maxTriangle.secondCoordinate}\n" +
                "C(${maxTriangle.thirdCoordinate}\n" +
                "P = ${maxTriangle.calcluatePerimetr()}"
    }


    data class TrianglePerimetr(val firstCoordinate: Pair<Double,Double> ,val secondCoordinate: Pair<Double,Double> ,val thirdCoordinate: Pair<Double,Double>){

        fun compareTo(value: TrianglePerimetr): Int{
            val initialLength = this.calcluatePerimetr()
            val compareLength = value.calcluatePerimetr()

            if(initialLength > compareLength)
                return 1
            else if(initialLength == compareLength)
                return 0
            else return -1

        }

        override fun equals(other: Any?): Boolean {
            val value = other as TrianglePerimetr
            return this.contains(value.firstCoordinate) && this.contains(value.secondCoordinate) &&this.contains(value.thirdCoordinate)
        }

        fun contains(value: Pair<Double,Double>): Boolean{
            return firstCoordinate == value || secondCoordinate == value || thirdCoordinate == value
        }

        fun calcluatePerimetr(): Double{
            val firstLength = Math.sqrt(Math.pow(firstCoordinate.first - secondCoordinate.second,2.0) + Math.pow(firstCoordinate.second - secondCoordinate.second,2.0))
            val secondLength = Math.sqrt(Math.pow(secondCoordinate.first - thirdCoordinate.second,2.0) + Math.pow(secondCoordinate.second - thirdCoordinate.second,2.0))
            val thirdLength = Math.sqrt(Math.pow(firstCoordinate.first - thirdCoordinate.second,2.0) + Math.pow(firstCoordinate.second - thirdCoordinate.second,2.0))
            return firstLength + secondLength + thirdLength
        }
    }

}
